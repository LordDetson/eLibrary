function showProgress(data) {
    if (data.status === "begin") {
        setDisplay("block", "none");
    } else if (data.status === "success") {
        setDisplay("none", "block");
    }
}

function setDisplay(loaderWrapperDisplay, booksTableWrapperDisplay) {
    let loaderWrapper = document.getElementById("loader-wrapper");
    let booksTableWrapper = document.getElementById("books-table-wrapper");
    loaderWrapper.style.display = loaderWrapperDisplay;
    loaderWrapper.querySelector("div").style.display = loaderWrapperDisplay;
    booksTableWrapper.style.display = booksTableWrapperDisplay;
    booksTableWrapper.querySelector("table").style.display = booksTableWrapperDisplay;
}