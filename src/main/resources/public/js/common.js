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

    $.fn.createBarchart = function (optionvariables) {
        var chartContainer = $(this);
        var defaults = {
            'maxWidth': 244
        };
        var options = $.extend({}, defaults, optionvariables);
        var self = $(this),
            graphContainer = self.parent().find('.graph-container .graph'),
            barChart = $('<ul/>', { class: 'bar-chart' });

        barChart.appendTo(chartContainer);

        $.each(answerArray, function(index, value) {
            var chartAnswer = $('<li/>', { class: 'answer-' + index }),
                answerLabel = $('<span/>', { class: 'label', text: value }),
                percentageValue = percentageArray[index].toString(),
                answerPercentage = $('<span/>', { class: 'percentage', text: percentageValue.replace('.', ',') + '%' }),
                barTrack = $('<span/>', { class: 'bar-track' }),
                bar = $('<span />', { class: 'bar', style: 'width: ' + percentageValue + '%;' });

            chartAnswer.appendTo(barChart);
            answerLabel.appendTo(chartAnswer);
            answerPercentage.appendTo(chartAnswer);
            barTrack.appendTo(chartAnswer);
            bar.appendTo(barTrack);
        });

        if($('html').hasClass('canvas')) {
            barChart.chart(
                {
                    graphContainer: graphContainer
                }
            );
        }
    };

    $.fn.chart = function (optionvariables) {
        var chart = $(this);
        var defaults = {
            'canvasSize': 220,
            'graphContainer': $('.graph-container .graph')
        };
        var options = $.extend({}, defaults, optionvariables);

        return chart.each(function () {
            var listItem = chart.find('li'),
                listItems = listItem.length,
                canvas = document.createElement('canvas'),
                canvasWidth = options.canvasSize,
                canvasHeight = options.canvasSize,
                graphContainer = options.graphContainer,
                total = 0,
                totalPercentage = 0,
                data = [],
                newData = [],
                i = 0,
                startingAngle,
                arcSize,
                endingAngle;

            $.each(percentageArray, function(index, value) {
                newData.push(3.6 * value);
            });

            function sumTo(a, i) {
                var sum = 0;
                for (var j = 0; j < i; j++) {
                    sum += a[j];
                }
                return sum - 90;
            }

            function degreesToRadians(degrees) {
                return ((degrees * Math.PI)/180);
            }

            canvas.setAttribute('width', canvasWidth);
            canvas.setAttribute('height', canvasHeight);
            canvas.setAttribute('id', 'chartCanvas');
            graphContainer.append(canvas);

            var cvs = document.getElementById('chartCanvas'),
                ctx = cvs.getContext('2d'),
                centerX = canvasWidth / 2,
                centerY = canvasHeight / 2,
                radius = canvasWidth / 2;

            ctx.clearRect(0, 0, canvasWidth, canvasHeight);

            listItem.each(function(e) {
                startingAngle = degreesToRadians(sumTo(newData, i));
                arcSize = degreesToRadians(newData[i]);
                endingAngle = startingAngle + arcSize;
                ctx.beginPath();
                ctx.moveTo(centerX, centerY);
                ctx.arc(centerX, centerY, radius, startingAngle, endingAngle, false);
                ctx.closePath();
                ctx.fillStyle = $(this).find('.bar').css('backgroundColor');
                ctx.fill();
                ctx.restore();
                i++;
            });

            ctx.beginPath();
            ctx.moveTo(centerX, centerY);
            ctx.arc(centerX, centerY, radius * .45, 0, 2 * Math.PI, false);
            ctx.closePath();
            ctx.fillStyle = $('body').css('backgroundColor');
            ctx.fill();
        });
    };

    $('#live-poll-area .answer-list').createBarchart();


//end of $(document).ready() block
});