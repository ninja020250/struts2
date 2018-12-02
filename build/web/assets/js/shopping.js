
//function checkALl() {
//    var checkAllBtn = document.getElementById("checkAll");
//    var checkitems = [];
//    checkitems = document.getElementsByClassName("checkItems");
//    if (checkAllBtn.values == true) {
//        for (var i in checkitems) {
//            checkitems[i].values = true;
//        }
//    } else {
//        for (var i in checkitems) {
//            checkitems[i].values = false;
//        }
//    }
//}
function checkAll() {

    var checkAllBtn = document.getElementById("checkAll").checked;
    console.log(checkAllBtn);
    var checkitems = [];
    checkitems = document.getElementsByClassName("checkItems");
    if (checkAllBtn == true) {
        for (var i in checkitems) {
            checkitems[i].checked = true;
        }
    } else {
        for (var i in checkitems) {
            checkitems[i].checked = false;
        }
    }
}
function addItemsToCart() {
    var form = document.getElementById("form-add-to-cart").submit();
}