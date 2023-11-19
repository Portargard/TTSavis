var apiCustomer = "http://localhost:8080/savis/customer";
var apiLogin = "http://localhost:8080/savis/login";

var listCustomers = [];
var customerId = "";
getCustomer();

function getCustomer() {
  fetch(apiCustomer)
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      return data;
    })
    .then(function (customers) {
      listCustomers.length = 0;
      customers.forEach((customer) => {
        listCustomers.push(customer);
      });
    });
}

function login(data, callback) {
  var options = {
    method: "POST",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(apiLogin, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      const contentType = response.headers.get("Content-Type");
      if (contentType && contentType.includes("application/json")) {
        return response.json();
      } else {
        console.error("Invalid Content-Type:", contentType);
        return null; // Trả về null khi có lỗi
      }
    })
    .then(callback)
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
      return null;
    });
}

function handleLogin() {
  var btnSubmmit = document.getElementById("login");
  btnSubmmit.addEventListener("click", (event) => {
    event.preventDefault();
    var username = document.getElementById("username").value;
    var password = document.getElementById("password").value;
    var data = {
      email: username,
      password: password,
      };
      
    login(data, checkLogin);
  });
}
handleLogin();

function checkLogin(customer) {
    if (customer === null) {
    alert("Tài khoản hoặc mật khẩu sai");
    } else {
        window.localStorage.setItem("customerId", customer.id);
        window.localStorage.setItem("customerName", customer.hoTen);
        alert("Đăng nhập thành công");
        window.location.href = "TrangChu.html";
    }
}
