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

function showUploadFileForm() {

    var modalElement = $('#upload-file-modal');

        modalElement.modal({keyboard: true});
        modalElement.modal('show');
}

function showCreateFolderForm() {

    var modalElement = $('#create-folder-modal');

    modalElement.modal({keyboard: true});
    modalElement.modal('show');
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

    $(document).on('click', '#upload-file-button', function(e){
        e.preventDefault();

        showUploadFileForm();
    });

    $(document).on('click', '#folder-create-button', function(e){
        e.preventDefault();

        showCreateFolderForm();
    });

    $("#blocked-extensions-input").tagsinput({
        confirmKeys: [32]
    });

    $(document).on('submit', '#blocked-extension-form', function (e) {
        var frm = $('#blocked-extension-form');
        e.preventDefault();

        var data = {};

        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        data["propertyName"] = "file.blocked.extensions";
        data["propertyValue"] = data["blocked-extensions"].split(", ").join(";");

        console.log(JSON.stringify(data));

        if (frm.valid()) {
            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: JSON.stringify(data),
                success: function (callback) {
                    console.log(callback);
                },
                error: function (callback) {
                    console.log(callback);
                }
            });
        }
    });

    $(document).on('submit', '#max-size-form', function (e) {
        var frm = $('#max-size-form');
        e.preventDefault();

        var data = {};

        $.each(this, function (i, v) {
            var input = $(v);
            data[input.attr("name")] = input.val();
            delete data["undefined"];
        });

        data["propertyName"] = "file.maximum_size";
        data["propertyValue"] = data["max-size"];

        console.log(JSON.stringify(data));

        if (frm.valid()) {
            $.ajax({
                contentType: "application/json; charset=utf-8",
                dataType: "json",
                type: frm.attr('method'),
                url: frm.attr('action'),
                data: JSON.stringify(data),
                success: function (callback) {
                    console.log(callback);
                },
                error: function (callback) {
                    console.log(callback);
                }
            });
        }


    });

    $('.delete-file').click(function(e) {
        e.preventDefault();
        var temp = $(this);

            $.ajax({
             contentType: "application/json; charset=utf-8",
             dataType: "json",
             type: "DELETE",
             url: ctx + $(this).attr('href'),
             success: function(callback) {
             temp.closest('.file').remove();
             },
             error: function(callback) {
             console.log("File deleting request failed!");
             }
             });
    });

    $('.download-file').click(function(e){

        window.location.href = $(this).attr('href');

    });

//end of $(document).ready() block
});