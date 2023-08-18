package controller;

import bo.BOFactory;
import bo.custom.ItemBO;
import com.jfoenix.controls.JFXButton;
import dto.ItemDTO;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.event.EventHandler;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;


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
    public JFXButton btnADD;
    private int selectedIndex = -1;
    private ObservableList<ItemDTO> allItems;
    ItemBO itemBO = (ItemBO) BOFactory.getInstance().getBO(BOFactory.BoTypes.ITEM);
    private boolean isEdit = false;

    public void initialize(){

        generateAndSetNextId();

        colItemID.setCellValueFactory(new PropertyValueFactory<>("itemID"));
        colItemName.setCellValueFactory(new PropertyValueFactory<>("itemName"));
        colBatchNo.setCellValueFactory(new PropertyValueFactory<>("batchNumber"));
        colItemPrice.setCellValueFactory(new PropertyValueFactory<>("price"));
        colExpireDate.setCellValueFactory(new PropertyValueFactory<>("expireDate"));
        colSupplier.setCellValueFactory(new PropertyValueFactory<>("supplier"));

        setDataToTable();

        // Set listener to the table
        tblItems.getSelectionModel().selectedIndexProperty().addListener((observable, oldValue, newValue) -> {
            selectedIndex = (int) newValue;
        });

    }

    private void loadItemDataToFields(ItemDTO selectedItem){
        ItemDTO selectedItemDetails = itemBO.getItemByID(selectedItem.getItemID());
        txtSupplierName.setText(selectedItemDetails.getSupplier());
        txtBatchNumber.setText(selectedItemDetails.getBatchNumber());
        txtItemId.setText(selectedItemDetails.getItemID());
        txtItemPrice.setText(String.format("%.2f",selectedItemDetails.getPrice()));
        pickerExpireDate.getEditor().setText(String.valueOf(selectedItemDetails.getExpireDate()));
        txtItemName.setText(selectedItemDetails.getItemName());
        txtQty.setText(String.format("%.2f",selectedItemDetails.getQty()));
    }

    private void generateAndSetNextId(){
        txtItemId.setText(itemBO.getNextID());
    }

    private void setDataToTable() {
        allItems = itemBO.getAllItems();
        tblItems.setItems(allItems);
    }

    public void btnAddOnAction(ActionEvent actionEvent) {

        if(!isEdit){
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
        }else {
            // Item Update method
            boolean updateResult = itemBO.updateItem(new ItemDTO(
                    txtItemId.getText(),
                    txtItemName.getText(),
                    txtBatchNumber.getText(),
                    Double.parseDouble(txtItemPrice.getText()),
                    Double.parseDouble(txtQty.getText()),
                    txtSupplierName.getText(),
                    Date.valueOf(LocalDate.now())
            ));

            if(updateResult){
                Alert alert = new Alert(Alert.AlertType.INFORMATION,"Successfully Updated!");
                alert.show();
                clearFields();
                initialize();
            }else{
                System.out.println("Not Updated!");
            }
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

    public void btnEditOnAction(ActionEvent actionEvent) {
        if(selectedIndex != -1){
            loadItemDataToFields(allItems.get(selectedIndex));
            btnADD.setText("UPDATE");
            btnADD.setStyle("-fx-background-color:  #f1c40f");
            isEdit = true;
        }else{
            Alert alert = new Alert(Alert.AlertType.ERROR,"Please select item first.");
            alert.show();
        }

    }

    public void btnDeleteOnAction(ActionEvent actionEvent) {

    }
}