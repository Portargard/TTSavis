var apiBookDetail = "http://localhost:8080/savis/book";
var apiAuthor = "http://localhost:8080/savis/author/list";
var apiPulisher = "http://localhost:8080/savis/pulisher/list";
var apiRealm = "http://localhost:8080/savis/realm/list";

var listRealms = [];
var listAuthors = [];
var listPulishers = [];
var image = "";

var idBookDetail = "";
function detailBook() {
  var urlParams = new URLSearchParams(window.location.search);
  var id = urlParams.get("id");
  idBookDetail = id;
}
detailBook();

function start() {
  getAllRealm(renderRealm);
  getAllPulisher(renderPulisher);
  getAllAuthor(renderAuthor);
  getBookDetail(renderBookDetail);
}
start();

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
  if (book.sale) {
    sale = book.giaBan - book.sale.giaTri;
    price = sale.toLocaleString("en-US") + "đ";
    price_sale = book.giaBan.toLocaleString("en-US") + "đ";
  } else {
    price_sale = book.giaBan.toLocaleString("en-US") + "đ";
  }
  const base64String = book.hinhAnh;
  var src = "data:image/png;base64," + base64String;
  document.getElementById("name").value = book.ten;
  document.querySelector("#realm").value = book.realm.id;
  document.querySelector("#author").value = book.author.id;
  document.querySelector("#pulisher").value = book.pulisher.id;
  document.querySelector("#weight").value = book.trongLuong;
  document.querySelector("#quantity").value = book.soLuong;
  document.querySelector("#importPrice").value = book.giaNhap;
  document.querySelector("#price").value = book.giaBan;
    document.querySelector("#description").value = book.moTa;
    if (book.trangThai == 1) {
        document.querySelector("#trangThai").checked = true;
    } else { 
        document.querySelector("#trangThai2").checked = true;
    }
  var imageElement = document.getElementById("selectedImage");
  imageElement.src = src;
    image = base64String;
    
}

function getAllRealm(callback) {
  fetch(apiRealm)
    .then(function (response) {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then(callback)
    .catch(function (error) {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function getAllPulisher(callback) {
  fetch(apiPulisher)
    .then(function (response) {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then(callback)
    .catch(function (error) {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function getAllAuthor(callback) {
  fetch(apiAuthor)
    .then(function (response) {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then(callback)
    .catch(function (error) {
      console.error("There was a problem with the fetch operation:", error);
    });
}

function renderRealm(realms) {
  var select = document.getElementById("realm");

  var htmls = realms.map(function (realm) {
    listRealms.push(realm);
    return `
      <option value="${realm.id}">${realm.tenLinhVuc}</option>
    `;
  });
  var html = htmls.join("");
  select.innerHTML = html;
}

function renderPulisher(pulishers) {
  var select = document.getElementById("pulisher");
  var htmls = pulishers.map(function (pulisher) {
    listPulishers.push(pulisher);
    return `
      <option value="${pulisher.id}">${pulisher.tenNXB}</option>
    `;
  });
  var html = htmls.join("");
  select.innerHTML = html;
}

function renderAuthor(authors) {
  var select = document.getElementById("author");
  var htmls = authors.map(function (author) {
    listAuthors.push(author);
    return `
      <option value="${author.id}">${author.tenTacGia}</option>
    `;
  });
  var html = htmls.join("");
  select.innerHTML = html;
}

function loadImage() {
  document.getElementById("image").addEventListener("change", function (event) {
    var input = event.target;

    if (input.files && input.files[0]) {
      var reader = new FileReader();

      reader.onload = function (e) {
        var imageElement = document.getElementById("selectedImage");
        imageElement.src = e.target.result;
      };

      reader.readAsDataURL(input.files[0]);
    }
  });
}
loadImage();

function updateBook(data) {
  var options = {
    method: "PUT",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(apiBookDetail + "/" + idBookDetail, options)
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

function convertToBase64(file, callback) {
  // Kiểm tra xem file có tồn tại không
  if (!file) {
    callback("Vui lòng chọn một tệp hình ảnh.", null);
    return;
  }

  // Sử dụng FileReader để đọc tệp
  var reader = new FileReader();

  // Xử lý sự kiện khi đọc tệp hoàn tất
  reader.onload = function (e) {
    // Lấy dữ liệu base64 từ sự kiện và gọi callback với giá trị
    var base64String = e.target.result.split(",")[1];
    callback(null, base64String);
  };

  // Xử lý sự kiện lỗi
  reader.onerror = function (error) {
    callback("Đã xảy ra lỗi khi đọc tệp.", null);
  };

  // Đọc tệp hình ảnh và chuyển đổi thành base64
  reader.readAsDataURL(file);
}

function handleUpdateForm() {
  var btnSubmmit = document.getElementById("update");
  btnSubmmit.onclick = function (event) {
    event.preventDefault();
    var bookName = document.querySelector("#name").value.trim();
    var realmId = document.querySelector("#realm").value;
    var authorId = document.querySelector("#author").value;
    var pulisherId = document.querySelector("#pulisher").value;
    var weight = document.querySelector("#weight").value;
    var quantity = document.querySelector("#quantity").value;
    var importPrice = document.querySelector("#importPrice").value;
    var price = document.querySelector("#price").value;
      var description = document.querySelector("#description").value;
      var trangThai = document.querySelector(
        'input[name="trangThai"]:checked'
      ).value;
    var imageBook = document.getElementById("image");
    var realmObject = {};
    var authorObject = {};
    var pulisherObject = {};

    listRealms.forEach(function (realm) {
      if (realm.id == realmId) {
        realmObject = { ...realm };
        return;
      }
    });

    listAuthors.forEach((author) => {
      if (author.id == authorId) {
        authorObject = { ...author };
        return;
      }
    });

    listPulishers.forEach((pulisher) => {
      if (pulisher.id == pulisherId) {
        pulisherObject = { ...pulisher };
        return;
      }
    });
    if (imageBook.files.length <= 0) {
      var formData = {
        ten: bookName,
        realm: realmObject,
        author: authorObject,
        pulisher: pulisherObject,
        trongLuong: weight,
        soLuong: quantity,
        soLuongDaBan: 0,
        giaNhap: importPrice,
        giaBan: price,
        hinhAnh: image,
        moTa: description,
        trangThai: trangThai
      };

      updateBook(formData);
      alert("Sửa thành công");
      window.location.href = "books.html";
    } else {
      var file = image.files[0];

      convertToBase64(file, function (error, base64String) {
        if (error) {
          alert(error);
        } else {
          var formData = {
            ten: bookName,
            realm: realmObject,
            author: authorObject,
            pulisher: pulisherObject,
            trongLuong: weight,
            soLuong: quantity,
            soLuongDaBan: 0,
            giaNhap: importPrice,
            giaBan: price,
            hinhAnh: base64String,
            moTa: description,
            trangThai: trangThai,
          };

          updateBook(formData);
          alert("Sửa thành công");
          window.location.href = "books.html";
        }
      });
    }
  };
}
handleUpdateForm();
