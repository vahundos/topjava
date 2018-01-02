var ajaxUrl = "ajax/admin/users/";
var datatableApi;

// $(document).ready(function () {
$(function () {
    datatableApi = $("#datatable").DataTable({
        "paging": false,
        "info": true,
        "columns": [
            {
                "data": "name"
            },
            {
                "data": "email"
            },
            {
                "data": "roles"
            },
            {
                "data": "enabled"
            },
            {
                "data": "registered"
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

    $(".userActivity").change(function () {
        var active = this.checked;
        var userId = $(this).closest("tr").attr("id");
        $.ajax({
            url: ajaxUrl + "availability",
            type: "POST",
            data: {
                id: userId,
                enabled: active
            },
            success: function () {
                successNoty("Attribute changed");
            }
        });
    })
});