function checkIsNotEmptyUsername(form, message) {
    let inputUsername = form[form.id + ":username"];
    if (inputUsername.value === '') {
        alert(message);
        inputUsername.focus();
        return false;
    }
    return true;
}