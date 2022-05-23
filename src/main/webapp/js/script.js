document.addEventListener('DOMContentLoaded', () => {
    'use strict'

    let password = document.getElementById("password");
    let confirmPassword = document.getElementById("confPassword");

    password.onchange = ConfirmPassword;
    confirmPassword.onkeyup = ConfirmPassword;

    function ConfirmPassword() {
        confirmPassword.setCustomValidity("");
        if (password.value !== confirmPassword.value) {
            confirmPassword.setCustomValidity("Passwords do not match.");
        }
    }
})