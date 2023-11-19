const apiBookDetail = "http://localhost:8080/savis/book";
const apiBill = "http://localhost:8080/savis/bill";
const apiBillDetail = "http://localhost:8080/savis/bill-detail";
var customerAPI = "http://localhost:8080/savis/customer";

var customerId = window.localStorage.getItem("customerId");
var idBookDetail = "";
var bookDetail = [];
function detailBook() {
  var urlParams = new URLSearchParams(window.location.search);
  var id = urlParams.get("id");
  idBookDetail = id;
}
detailBook();

function start() {
  getBookDetail(renderBookDetail);
}

start();

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

function getBookDetail(callback) {
  fetch(apiBookDetail + "/" + idBookDetail)
    .then(function (response) {
      return response.json();
    })
    .then(function (data) {
      return data;
    })
    .then(callback);
}

function renderBookDetail(book) {
  var price = "";
  var price_sale = "";
  var sale = "";
  if (book.sale!=null) {
    sale = book.giaBan - book.sale.giaTri;
    price = sale.toLocaleString("en-US") + "đ";
    price_sale = book.giaBan.toLocaleString("en-US") + "đ";
  } else {
    price_sale = book.giaBan.toLocaleString("en-US") + "đ";
  }
  const base64String = book.hinhAnh;
  var src = "data:image/png;base64," + base64String;
  document.getElementById("nameBook").innerText = book.ten;
  document.getElementById("publisher").innerText = book.pulisher.tenNXB;
  document.getElementById("author").innerText = book.author.tenTacGia;

  document.getElementById("price-sale").innerText = price_sale;
  document.getElementById("begin-price").innerText = price;
  document.getElementById("image-name").src = src;
  document.getElementById("table-ma").innerText = book.ma;
  document.getElementById("table-publisher").innerText = book.pulisher.tenNXB;
  document.getElementById("table-author").innerText = book.author.tenTacGia;
  document.getElementById("table-weight").innerText = book.trongLuong;
  document.getElementById("story").innerText = book.moTa;
  bookDetail.push(book);
}

// giao dien
function validateNumber(input) {
  input.value = input.value.replace(/[^\d]+/g, "");
  // input.value = input.value.replace(/[]/g, "");
}
function validateQty() {
  var qty = document.getElementById("qty");
  if (qty.value > 999) {
    qty.value = 999;
  }
  if (!qty.value) {
    qty.value = 1;
  }
}

function subtractQty() {
  var qty = document.getElementById("qty");
  var number = Number(qty.value);
  if (qty.value > 1) {
    number -= 1;
  }
  qty.value = number;
}
function addQty() {
  var qty = document.getElementById("qty");
  var number = Number(qty.value);
  if (qty.value <= 999) {
    number += 1;
  }
  qty.value = number;
}

function createBill(data) {
  var options = {
    method: "POST",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(apiBill, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function createBillDetail(data) {
  var options = {
    method: "POST",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(apiBillDetail, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function getBill(callback) {
  fetch(apiBill + "/customer/" + customerId)
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

function handleBillDetail(bill) {
  var quantity = document.getElementById("qty").value;
  var price_before = document.getElementById("price-sale").innerText;
  var numericPrice = parseFloat(price_before.replace(/[^\d.]/g, ""));
  var sum = quantity * numericPrice;
  if (bill == null) {
    var customerObj = {};
    listCustomers.forEach((customer) => {
      if (customer.id == customerId) {
        customerObj = { ...customer };
        return;
      }
    });
    var formData = {
      customer: customerObj,
    };
    createBill(formData);
  } else {
    var formBillDetail = {
      bill: bill,
      book: bookDetail[0],
      soLuong: quantity,
      tongTien: sum,
    };
    createBillDetail(formBillDetail);
    alert("thêm thành công");
  }
}

function addToCart() {
  var btnAddToCart = document.getElementById("addToCart");
  btnAddToCart.addEventListener("click", () => {
    if (
      customerId == null ||
      customerId == undefined ||
      customerId.trim().length == 0
    ) {
      window.location.href = "DangNhap.html";
    }

    getBill(handleBillDetail);
  });
}
addToCart();

function BuyNow() {
  var buy = document.getElementById("buyNow");
  buy.addEventListener("click", () => {
    if (
      customerId == null ||
      customerId == undefined ||
      customerId.trim().length == 0
    ) {
      window.location.href = "DangNhap.html";
    }
    window.location.href = "ThanhToan.html";
  });
}
BuyNow();
