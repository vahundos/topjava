var ajaxUrl = "ajax/meals/";
var datatableApi;

$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "dateTime"
            },
            {
                "data": "description"
            },
            {
                "data": "calories"
            },
            {
                "defaultContent": "Edit",
                "orderable": false
            },
            {
                "defaultContent": "Delete",
                "orderable": false
            }
        ],
        "order": [
            [
                0,
                "asc"
            ]
        ]
    });
    makeEditable();

    $("input[name='startDate'], input[name='endDate']").datetimepicker({
        timepicker: false,
        format: 'd-m-Y'
    });

    $("input[name='startTime'], input[name='endTime']").datetimepicker({
        datepicker: false,
        format: 'H:i'
    });

    $("#dateTime").datetimepicker({
        format:'d-m-Y H:i'
    });

    $("#applyFilter").click(function() {
        applyFilter();
        return false;
    });

    $("#clearFilter").click(function () {
        clearFilter();
        return false;
    })
});

function applyFilter() {
    var form = $("#filterForm");
    $.ajax({
        url: ajaxUrl + "/filter",
        type: "POST",
        data: form.serialize(),
        success: function (data) {
            datatableApi.clear().rows.add(data).draw();
            successNoty("Filtered")
        }
    });
}

function clearFilter() {
    var inputs = $("#filterForm").find(":input");
    $(inputs).each(function () {
            $(this).val("");
    });
    applyFilter();
}