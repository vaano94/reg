$(document).ready(function(){
    $("#signup_form_div").hide();

    $("#button_id").click(function(){
        $(this).hide();
        $("#signup_form_div").show();
    });

});