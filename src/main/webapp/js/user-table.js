// Khi nào trang html nội dung dc nạp vào trình duyệt
// thì sẽ chạy code bên trong html
$(document).ready(function(){
    $(".btn-delete-user").click(function(){
        var id = $(this).attr("userid")
        var This = $(this)
        $.ajax({
          method: "GET",
          url: "http://localhost:8080/demoservlet/user/delete?id=" + id,
        }).done(function(result) {
            This.closest("tr").remove();
            console.log("Ket qua",result)
          });
        Retrieve the latest version o
    })
})
