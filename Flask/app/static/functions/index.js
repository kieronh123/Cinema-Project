function searchFunction() {
    var input, filter, ul, li, h3, i;
    input = document.getElementById('myinput');
    filter = input.value.toUpperCase();
    ul = document.getElementById('wrapper');
    li = ul.getElementsByTagName('li');

    for(i=0 ; i< li.length; i++){
        h3 = li[i].getElementsByTagName('h3')[0];
        if(h3.innerHTML.toUpperCase().indexOf(filter) > -1){
            li[i].style.display = "";
        }
        else{
            li[i].style.display = 'none';
        }
    }
}
