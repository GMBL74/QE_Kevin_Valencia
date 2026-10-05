package com.kevinvalencia.saucedemo.stepdefinitions;

import com.kevinvalencia.saucedemo.steps.LoginSteps;
import io.cucumber.java.Before;
import io.cucumber.java.Scenario;
import io.cucumber.java.en.Given;
import io.cucumber.java.en.Then;
import io.cucumber.java.en.When;

public class LoginStepDefinitions {

    private final LoginSteps loginSteps;

    public LoginStepDefinitions(LoginSteps loginSteps) {
        this.loginSteps = loginSteps;
    }

    // Traza en consola para identificar cada escenario en la ejecución del pipeline
    @Before(order = 0)
    public void logInicioEscenario(Scenario scenario) {
        System.out.println(">>> Iniciando escenario: " + scenario.getName());
    }

    @Given("que estoy en la pagina de inicio de sesion")
    public void estoyEnLaPaginaDeInicioDeSesion() {
        loginSteps.openLogin();
    }

    @When("inicio sesion con el usuario {string}")
    public void inicioSesionConElUsuario(String userType) {
        loginSteps.loginAs(userType);
    }

    @Then("deberia obtener el resultado de login {string}")
    public void deberiaObtenerElResultadoDeLogin(String result) {
        loginSteps.assertLoginResult(result);
    }

    @Then("deberia visualizar la pagina de productos")
    public void deberiaVisualizarLaPaginaDeProductos() {
        loginSteps.assertProductsPageIsDisplayed();
    }

    @Then("deberia visualizar el mensaje de error {string}")
    public void deberiaVisualizarElMensajeDeError(String message) {
        loginSteps.assertLoginErrorContains(message);
    }

    @Then("deberia visualizar nuevamente el formulario de inicio de sesion")
    public void deberiaVisualizarNuevamenteElFormularioDeInicioDeSesion() {
        loginSteps.assertLoginFormIsVisible();
    }
}
