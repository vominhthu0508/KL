package GUI;

import DTO.KhoaLuanDTO;
import BLL.KhoaLuanBLL;
import GUI.MainFunction;
import java.awt.Color;
import java.net.URL;
import java.util.ArrayList;
import java.util.Collections;
import java.util.ResourceBundle;
import java.util.logging.Level;
import java.util.logging.Logger;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.fxml.Initializable;
import javafx.scene.control.Button;
import javafx.scene.control.Label;
import javafx.scene.control.PasswordField;
import javafx.scene.control.TableColumn;
import javafx.scene.control.TableView;
import javafx.scene.control.TextField;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.layout.AnchorPane;

/**
 * Login Controller.
 */
public class MainController extends AnchorPane implements Initializable {

    KhoaLuanBLL bll = new KhoaLuanBLL();
    MainFunction func = new MainFunction();
    ArrayList<KhoaLuanDTO> dataList = new ArrayList<KhoaLuanDTO>();
    
    @FXML 
    private TextField textBox;
    @FXML 
    private TableView<KhoaLuanDTO> dataTable;
    @FXML
    private TableColumn<KhoaLuanDTO, Integer> indexOfTupleCol;
    @FXML
    private TableColumn<KhoaLuanDTO, String> productIdCol;
    @FXML
    private TableColumn<KhoaLuanDTO, Integer> scoreCol;
    @FXML
    private TableColumn<KhoaLuanDTO, Float> proCol;
    
    @FXML
    private TableView<KhoaLuanDTO> resultTable;
    @FXML
    private TableColumn<KhoaLuanDTO, Integer> indexOfTupleCol2;
    @FXML
    private TableColumn<KhoaLuanDTO, String> productIdCol2;
    @FXML
    private TableColumn<KhoaLuanDTO, Integer> scoreCol2;
    @FXML
    private TableColumn<KhoaLuanDTO, Float> proCol2;
    @FXML
    private TableColumn<KhoaLuanDTO, Float> proTopkCol;

    @Override
    public void initialize(URL location, ResourceBundle resources) {
        indexOfTupleCol.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Integer>("indexOfTuple"));
        productIdCol.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, String>("productId"));
        scoreCol.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Integer>("score"));
        proCol.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Float>("pro"));
        try {
            dataTable.getItems().setAll(getDataList());
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
    }
    
    private ArrayList<KhoaLuanDTO> getDataList() throws Exception{
        dataList = bll.getAllData();
        Collections.sort(dataList, new SortByRoll());
        return dataList;
    }
    
    public void btnClick (ActionEvent event) {
        ArrayList<KhoaLuanDTO> topkTuples = new ArrayList<KhoaLuanDTO>();
        ArrayList<KhoaLuanDTO> PTkTuples = new ArrayList<KhoaLuanDTO>();
        try {
            topkTuples = func.GetSequenceTopkBestPro(dataList, Integer.valueOf(textBox.getText()));
            //PTkTuples = GetSequencePTk(dataList, Integer.valueOf(jTextField1.getText()), 0.031f);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
        
        indexOfTupleCol2.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Integer>("indexOfTuple"));
        productIdCol2.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, String>("productId"));
        scoreCol2.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Integer>("score"));
        proCol2.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Float>("pro"));
        proTopkCol.setCellValueFactory(new PropertyValueFactory<KhoaLuanDTO, Float>("topk"));
        try {
            resultTable.getItems().setAll(topkTuples);
        } catch (Exception ex) {
            Logger.getLogger(MainController.class.getName()).log(Level.SEVERE, null, ex);
        }
      
   }
}
