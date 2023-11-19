var customerAPI = "http://localhost:8080/savis/customer";
var urlParams = new URLSearchParams(window.location.search);

var id = window.localStorage.getItem("customerId"); 
var listCustomers = [];
getAllCustomer();
function getAllCustomer() {
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

function getCustomers(callback) {
  fetch(customerAPI+'/'+id)
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      return data;
    }).then(callback);
}
getCustomers(renderCustomer);

function renderCustomer(customer) {
  console.log(customer);
  document.querySelector('input[name="fullName"]').value = customer.hoTen;
  document.querySelector('input[name="phoneNumber"]').value = customer.sdt;
  document.querySelector('input[name="address"]').value = customer.diaChi;
  if (customer.gioiTinh == 1) {
    document.getElementById('gioiTinh1').checked = true;
  } else {
    document.getElementById("gioiTinh2").checked = true;
  }
  document.querySelector('input[name="email"]').value = customer.email;
  document.querySelector('input[name="account"]').value = customer.username;
  document.querySelector('input[name="address"]').value = customer.diaChi;
  document.querySelector('input[name="password"]').value = customer.password;
}

function UpdateCustomer(data) {
  var options = {
    method: "PUT",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(customerAPI+'/'+id, options)
    .then((response) => response.json())
    .then((data) => {
      
      return data;
    });
}

function handleCreateForm() {
  var btnSubmmit = document.getElementById("update");
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
    var listCustomers2 = [];
    listCustomers.forEach(customer => {
      if (customer.sdt != sdt) {
        listCustomers2.push(customer);
      }
    })
    listCustomers2.forEach((customer) => {
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
    var listCustomers3 = [];
    listCustomers.forEach((customer) => {
      if (customer.username != username) {
        listCustomers3.push(customer);
      }
    });
    listCustomers3.forEach((customer) => {
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

    };

    UpdateCustomer(formData);

    alert("Sửa thành công");
    // window.location.href = "TrangChu.html";
  };
}
handleCreateForm();

