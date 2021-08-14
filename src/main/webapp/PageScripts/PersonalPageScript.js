function searchHelper() {
    let input, filter, menu, l;

    // get all needed fields
    input = document.getElementById("profile_search");
    filter = input.value.toUpperCase();
    menu = document.getElementById("menu");
    l = menu.getElementsByTagName("li");


    let a;
    for (let i = 0; i < l.length; i++) { // hide unnecessary things
        a = l[i].getElementsByTagName("a")[0];
        if (a.innerHTML.toUpperCase().indexOf(filter) > -1) {
            l[i].style.display = "";
        } else {
            l[i].style.display = "none";
        }
    }
}