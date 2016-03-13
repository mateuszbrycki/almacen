function changeLanguage(data) {

    $.ajax({
        contentType: "application/text; charset=utf-8",
        type: "GET",
        url: ctx + "?language=" + data,
        success: function (callback) {
            location.reload();
        },
        error: function (callback) {
            console.log(callback);
        }
    });
}

function changeLoadingOverlay(flag) {
    if (flag) {
        document.getElementById('loading-overlay').style.display = 'block';
    } else {
        document.getElementById('loading-overlay').style.display = 'none';
    }
}

$(document).ready(function () {

    //language select
    if ($.cookie(languageCookieName)) {
        $('#language-select').val($.cookie(languageCookieName));
    } else {
        $("#language-select").val($("#language-select").val());
    }
    try {
        $("#language-select").msDropDown();
        $("#language-select_msdd").width(80);
    } catch (e) {
        console.log(e.message);
    }

});