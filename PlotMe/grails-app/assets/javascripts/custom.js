/**
 * Created by Saim on 4/1/2015.
 */
$(document).ready(function() {

    $("#menu-toggle").click(function(e) {
        e.preventDefault();
        $("#wrapper").toggleClass("toggled");

        var arrow = $(this).text().trim();

        if (arrow === '<') {
            $(this).text('>');
        } else if (arrow === '>') {
            $(this).text('<');
        }
    });
});
