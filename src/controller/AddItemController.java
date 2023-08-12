package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import dto.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.KeyEvent;

import java.sql.Date;
import java.time.LocalDate;

public class AddItemController {
    public TextField txtItemId;
    public TextField txtItemName;
    public TextField txtBatchNumber;
    public TextField txtSupplierName;
    public TextField txtItemPrice;
    public DatePicker pickerExpireDate;
    public TextField txtQty;
    public TableColumn colItemID;
    public TableColumn colItemName;
    public TableColumn colBatchNo;
    public TableColumn colItemPrice;
    public TableColumn colSupplier;
    public TableColumn colExpireDate;
    public TableView tblItems;


    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.ITEM);

    public void initialize(){

        generateAndSetNextId();

        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        setDataToTable();

    }

    private void generateAndSetNextId(){
        txtItemId.setText(itemBO.getNextID());
    }

    private void setDataToTable() {
        ObservableList<ItemDTO> allItems = itemBO.getAllItems();
        tblItems.setItems(allItems);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        ItemDTO itemDTO = new ItemDTO(
                txtItemId.getText(),
                txtItemName.getText(),
                txtBatchNumber.getText(),
                Double.parseDouble(txtItemPrice.getText()),
                Double.parseDouble(txtQty.getText()),
                txtSupplierName.getText(),
                Date.valueOf(LocalDate.now())
        );

        boolean b = itemBO.saveItem(itemDTO);

        if(b){
            Alert alert = new Alert(Alert.AlertType.INFORMATION,"Item Saved!");
            alert.show();

            // Clear Fields
            clearFields();

            // Generate Next ItemID
            generateAndSetNextId();

            //Reload the table
            setDataToTable();
        }

    }

    public void clearFields(){
        txtItemId.clear();
        txtItemName.clear();
        txtItemPrice.clear();
        txtBatchNumber.clear();
        txtSupplierName.clear();
        txtQty.clear();
        pickerExpireDate.getEditor().clear();
    }

}