package org.example.view.viewcontroller.db;

import org.example.db.CategoryInterfaceImpl;
import org.example.db.IODetailInterfaceImpl;
import org.example.db.ItemInterfaceImpl;
import org.example.db.ManagerInterfaceImpl;
import org.example.dto.CreateIODetail;
import org.example.dto.CreateIODetail.InAndOut;
import org.example.dto.FilteredKeyword;
import org.example.dto.IODetail;
import org.example.view.model.*;
import org.example.view.viewcontroller.*;

import java.time.LocalDate;
import java.time.ZonedDateTime;

public class DBViewControllerContainer implements ViewControllerContainer {

    @Override
    public GetManagerList getManagerList() {
        return () ->
                ManagerInterfaceImpl.getInstance().getManagers().stream().map(it ->
                        new Manager(it.id(), it.name(), it.grade())
                ).toList();
    }

    @Override
    public CreateManager createManager() {
        return (name, grade) ->
                ManagerInterfaceImpl.getInstance().createManager(
                        new org.example.dto.CreateManager(name, grade)
                );
    }

    @Override
    public GetItemList getItemList() {
        return (page, search, categoryID) -> {
            boolean keywordIsNull = search == null || search.isBlank();
            boolean categoryIsNull = categoryID == null || categoryID.isBlank();
            if (keywordIsNull && categoryIsNull) {
                return ItemInterfaceImpl.getInstance().getItems(page).stream()
                        .map(this::toModel).toList();
            }
            if (!keywordIsNull && categoryIsNull) {
                return ItemInterfaceImpl.getInstance().searchItems(search, page)
                        .stream().map(this::toModel).toList();
            }
            if (!keywordIsNull && !categoryIsNull) {
                return ItemInterfaceImpl.getInstance().searchFilteredItems(new FilteredKeyword(categoryID, search), page)
                        .stream().map(this::toModel).toList();
            }
            return ItemInterfaceImpl.getInstance().filteredItems(categoryID, page)
                    .stream().map(this::toModel).toList();
        };
    }

    @Override
    public CreateCategory createCategory() {
        return (category) ->
                CategoryInterfaceImpl.getInstance().createCategory(category);
    }

    @Override
    public GetCategoryList getCategoryList() {
        return () -> CategoryInterfaceImpl.getInstance().getCategories().stream()
                .map(c -> new Category(c, c)).toList();
    }

    @Override
    public CreateItem createItem() {
        return (name, id) -> ItemInterfaceImpl.getInstance().createItem(
                new org.example.dto.CreateItem(name, id));
    }

    @Override
    public GetItem getItem() {
        return (id) -> {
            var itemDto = ItemInterfaceImpl.getInstance().getItemById(id);

            return new Item(
                    itemDto.id(), itemDto.name(), itemDto.category(),
                    itemDto.category(), itemDto.quantity()
            );
        };
    }

    @Override
    public GetItemIOList getItemIOList() {
        return (itemID, page) ->
                IODetailInterfaceImpl.getInstance().getIODetailByItemId(itemID)
                        .stream().map(this::toModel).toList();
    }

    @Override
    public CreateItemIO createItemIO() {
        return (itemID, io, quantity, managerID) ->
                IODetailInterfaceImpl.getInstance().createIODetail(
                        new CreateIODetail(managerID, itemID, quantity,
                                LocalDate.now(),//Todo 시간을 안받음
                                io == io.IN ? InAndOut.IN : InAndOut.OUT
                        )
                );
    }

    @Override
    public DeleteItemIO deleteItemIO() {
        return (ioID) -> IODetailInterfaceImpl.getInstance().deleteIODetail(ioID);
    }

    private Item toModel(org.example.dto.Item i) {
        return new Item(i.id(), i.name(), i.category(), i.category(), i.quantity());
    }

    private ItemIO toModel(IODetail iod) {
        return new ItemIO(iod.id(), iod.quantity(),
                isIN(iod.inOut()) ? IO.IN : IO.OUT, iod.manager().name(),
                ZonedDateTime.now()//Todo IOD 레코드에 현재시간 출력이 없어서 임시로 현재시간 넣음
        );
    }

    private boolean isIN(String inOut) {
        return inOut.equals("IN");
    }
}