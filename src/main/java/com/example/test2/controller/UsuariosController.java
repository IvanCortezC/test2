package com.example.test2.controller;

import com.example.test2.dto.UsuarioDto;
import com.example.test2.model.UsuariosModel;
import javafx.collections.FXCollections;
import javafx.collections.ObservableList;
import javafx.event.ActionEvent;
import javafx.fxml.FXML;
import javafx.scene.control.*;
import javafx.scene.control.cell.PropertyValueFactory;
import javafx.scene.input.MouseEvent;

public class UsuariosController {

    private UsuarioDto usuarioAEliminar;
    @FXML
    public Button btnEliminar;
    @FXML
    public TableView tableUsuarios;
    @FXML
    public TableColumn<UsuarioDto, Integer> colId;
    @FXML
    public TableColumn<UsuarioDto, String> colNombre;
    @FXML
    public TableColumn<UsuarioDto, String> colProfesion;

    private Integer idUsuario = 0;
    private UsuariosModel usuariosModel;
    @FXML
    public TextField txtProfesion;
    @FXML
    public TextField txtNombre;
    @FXML
    public TextField txtResultado;

    public UsuariosController() {
        usuariosModel = new UsuariosModel();
    }

    private ObservableList<UsuarioDto> observableUsuarios;

    public void initialize() {

        usuariosModel = new UsuariosModel();
        this.colId.setCellValueFactory(new PropertyValueFactory<>("id"));
        this.colNombre.setCellValueFactory(new PropertyValueFactory<>("nombre"));
        this.colProfesion.setCellValueFactory(new PropertyValueFactory<>("profesion"));
        observableUsuarios = FXCollections.observableArrayList();
        tableUsuarios.setItems(observableUsuarios);
    }


    public void handleCrear(ActionEvent actionEvent) {

        try {
            String nombre = txtNombre.getText();
            String profesion = txtProfesion.getText();

            if (nombre.trim().isEmpty() || profesion.isEmpty()) {
                throw new Exception("Nombre o Profesion Invalido, no pueden estar vacios");
            }
            Integer idUsuario = this.getIdUsuario();

            UsuarioDto usuarioDto = new UsuarioDto();
            usuarioDto.setNombre(nombre);
            usuarioDto.setProfesion(profesion);
            usuarioDto.setId(idUsuario);

            this.usuariosModel.agregarUsuarios(usuarioDto);
            this.txtResultado.setText(String.valueOf(String.valueOf(this.usuariosModel.getUsuarios().size())));
            this.observableUsuarios.setAll(usuariosModel.getUsuarios());
        } catch (Exception e) {
            this.mostrarError("Probando");
        }
    }
        private Integer getIdUsuario () {
            this.idUsuario = this.idUsuario + 1;
            return idUsuario;
        }

        private void mostrarError (String mensaje){
            Alert alert = new Alert(Alert.AlertType.ERROR);
            alert.setTitle("Error");
            alert.setHeaderText("Error");
            alert.setContentText(mensaje);
            alert.showAndWait();
        }

    public void handleSelectTable(MouseEvent mouseEvent) {
        UsuarioDto usuarioDto = (UsuarioDto) this.tableUsuarios.getSelectionModel().getSelectedItem();
        System.out.println(usuarioDto);
        if (usuarioDto != null) {
            this.btnEliminar.setDisable(false);
            this.usuarioAEliminar = usuarioDto;

        }
    }

    public void handleEliminar(ActionEvent actionEvent) {
        this.usuariosModel.eliminar(this.usuarioAEliminar);
        this.observableUsuarios.setAll(usuariosModel.getUsuarios());
        this.btnEliminar.setDisable(true);
    }
}

