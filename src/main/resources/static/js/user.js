// 1. 이벤트 리스너

$("#btn-join").click(()=>{
    join();
});

// 2. 기능
async function join(){
    let joinDto = {
        username: $("#username").val(),
        nickname: $("#nickname").val(),
        password: $("#password").val(),
        email: $("#email").val(),
    }


    fetch("/join",{
        method : "POST"
    })
}