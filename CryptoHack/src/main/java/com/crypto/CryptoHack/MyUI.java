package com.crypto.CryptoHack;

import com.crypto.CryptoHack.dto.CryptoAPIClient;
import com.crypto.CryptoHack.dto.SellingComponent;
import com.vaadin.annotations.Theme;
import com.vaadin.annotations.Title;
import com.vaadin.server.VaadinRequest;
import com.vaadin.spring.annotation.SpringUI;
import com.vaadin.ui.*;
import org.slf4j.Logger;
import org.slf4j.LoggerFactory;
import org.springframework.beans.factory.annotation.Autowired;
import org.vaadin.viritin.components.DisclosurePanel;


@SpringUI
@Theme("valo")
@Title("Crypto Details")
public class MyUI extends UI {

    private static final Logger log = LoggerFactory.getLogger(MyUI.class);

    //private Grid<SellingDataStore> sellsLast = new Grid<>(SellingDataStore.class);
    private Grid<SellingComponent> sellsLast = new Grid<>(SellingComponent.class);
    @Autowired
    CryptoAPIClient cryptoAPIClient;


    @Override
    protected void init(final VaadinRequest request) {
        DisclosurePanel aboutBox = new DisclosurePanel("Crypto APi result");
        Label label = new Label("Menu");
        label.setValue("my menu%");
        MenuBar myMenu = new MenuBar();
        myMenu.setDescription(label.getValue());
        HorizontalLayout mainWind = new HorizontalLayout(

        );

        myMenu.addItem("Tabuler Report",null,null);

        myMenu.setWidth("20%");


        sellsLast.setWidth("100%");
        sellsLast.setHeaderVisible(true);
        //normal dead store
       sellsLast.setItems(cryptoAPIClient.getDatewise());
        sellsLast.removeAllColumns();
        sellsLast.addColumn(sellingDataStore -> sellingDataStore.getToDay()).setCaption("Current Day");
        sellsLast.addColumn(sellingDataStore -> sellingDataStore.getPrice()).setCaption("Today's Price");
        sellsLast.addColumn(sellingDataStore -> sellingDataStore.getMarketCap()).setCaption("MarketCap");
        sellsLast.addColumn(sellingDataStore -> sellingDataStore.getVolume()).setCaption("Volume");


        myMenu.addItem(cryptoAPIClient.getCryptoDTOSListforAPI().getMarketDetails().getMarketName(),null,null);



        myMenu.addItem(cryptoAPIClient.getCryptoDTOSListforAPI().getMarketDetails().getMarketLastUpdate().toString(),null,null);

        mainWind.addComponents(myMenu,sellsLast);

        mainWind.setSizeFull();
        /*mainLayout.addComponent(leaf);
        mainLayout.addComponent(details);*/
        setContent(mainWind);

    }
   /* public VerticalLayout jpaPagable() {

        final int page = 1;
        final int limit = 10;
        Pageable pageable = new PageRequest(page, limit);
        final Page<SellingDataStore> users = sellingDataStoreEntity.findAll(pageable);
        final long total = users.getTotalElements();

        final Grid grid = createGrid(users.getContent());
        final Pagination pagination = createPagination(total, page, limit);
        pagination.addPageChangeListener(new PaginationChangeListener() {
            @Override
            public void changed(PaginationResource event) {
                log.debug("jpaPagable : {}", event.toString());
                Page<User> users = findAll(event.pageIndex(), event.limit());
                pagination.setTotalCount(users.getTotalElements());
                grid.setItems(users.getContent());
                grid.scrollToStart();
            }
        });

        final VerticalLayout layout = createContent(grid, pagination);
        return layout;
    }
*/
}
