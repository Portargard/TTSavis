var customerAPI = "http://localhost:8080/savis/customer";

var listCustomers = [];

getCustomer();
function getCustomer() {
  fetch(customerAPI)
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



function createCustomer(data) {
  var options = {
    method: "POST",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(customerAPI, options)
    .then((response) => response.json())
    .then((data) => {
      nameCustomer = data.hoTen;
      return data;
    });
}

function handleCreateForm() {
  var btnSubmmit = document.getElementById("signUp");
  btnSubmmit.onclick = function (event) {
    event.preventDefault();
    var hoTen = document.querySelector('input[name="fullName"]').value.trim();
    var sdt = document.querySelector('input[name="phoneNumber"]').value.trim();
    var diaChi = document.querySelector('input[name="address"]').value.trim();
    var email = document.querySelector('input[name="email"]').value.trim();
    var username = document.querySelector('input[name="account"]').value.trim();
    var password = document
      .querySelector('input[name="password"]')
      .value.trim();
    var confirmPassword = document.querySelector(
      'input[name="confirmPassword"]'
    ).value;
    var gioiTinh = document.querySelector(
      'input[name="gioiTinh"]:checked'
    ).value;

    if (
      hoTen.length == 0 &&
      sdt.length == 0 &&
      diaChi.length == 0 &&
      email.length == 0 &&
      username.length == 0 &&
      password.length == 0 &&
      confirmPassword.length == 0
    ) {
      alert("cần nhập dữ liệu");
      return false;
    }

    if (hoTen.length == 0) {
      alert("Nhập họ tên");
      return false;
    }
    if (sdt.length == 0) {
      alert("Nhập số điện thoại");
      return false;
    }
    if (diaChi.length == 0) {
      alert("Nhập địa chỉ");
      return false;
    }
    if (email.length == 0) {
      alert("Nhập email");
      return false;
    }
    if (username.length == 0) {
      alert("Nhập tài khoản");
      return false;
    }
    if (password.length == 0) {
      alert("Nhập password");
      return false;
    }
    if (confirmPassword.length == 0) {
      alert("Nhập xác nhận mật khẩu");
      return false;
    }

    if (password != confirmPassword) {
      alert("Mật khẩu không đúng");
      return false;
    }

    if (!/^\d{10}$/.test(sdt)) {
      alert("Số điện thoại không đúng địng dạng");
      return false;
    }

    if (!/^[a-z0-9]+@[a-z]+\.[a-z]{2,3}$/.test(email)) {
      alert("Email không đúng định dạng");
      return false;
    }
    var check2 = false;
    listCustomers.forEach((customer) => {
      if (customer.sdt == sdt) {
        check2 = true;
        return;
      }
    });

    if (check2) {
      alert("Số điện thoại đã tồn tại!");
      document.querySelector('input[name="phoneNumber"]').focus();
      return false;
    }

    var check = false;
    listCustomers.forEach((customer) => {
      if (customer.username == username) {
        check = true;
        return;
      }
    });

    if (check) {
      alert("Tài khoản đã tồn tại!");
      document.querySelector('input[name="account"]').focus();
      return false;
    }

    var formData = {
      hoTen: hoTen,
      sdt: sdt,
      diaChi: diaChi,
      gioiTinh: gioiTinh,
      email: email,
      username: username,
      password: password,
      diemTichLuy: 0,
      trangThai: 0,
    };

    createCustomer(formData);
    window.localStorage.setItem("customer", nameCustomer);
    alert("Đăng ký thành công");
    window.location.href = "TrangChu.html";
  };
}
handleCreateForm();
