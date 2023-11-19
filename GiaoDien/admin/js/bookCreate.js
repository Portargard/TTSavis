var apiBook = "http://localhost:8080/savis/book";
var apiAuthor = "http://localhost:8080/savis/author/list";
var apiPulisher = "http://localhost:8080/savis/pulisher/list";
var apiRealm = "http://localhost:8080/savis/realm/list";

var listRealms = [];
var listAuthors = [];
var listPulishers = [];

function start() { 
  getAllRealm(renderRealm);
  getAllPulisher(renderPulisher);
  getAllAuthor(renderAuthor);
  
}
start();

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

function createBook(data) {
  var options = {
    method: "POST",
    headers: {
      "Content-type": "application/json; charset=UTF-8",
    },
    body: JSON.stringify(data),
  };
  fetch(apiBook, options)
    .then((response) => {
      if (!response.ok) {
        throw new Error("Network response was not ok");
      }
      return response.json();
    })
    .then((data) => {
      return data;
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

function handleCreateForm() {
  var btnSubmmit = document.getElementById("create");
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
    var image = document.getElementById("image");
    var file = image.files[0];

    var realmObject = {};
    var authorObject = {};
    var pulisherObject = {};

    listRealms.forEach(function (realm) { 
      if (realm.id == realmId) {
        realmObject = { ...realm };
        return;
      }
    })
    
    listAuthors.forEach((author) => { 
      if (author.id == authorId) {
        authorObject = { ...author };
        return;
      }
    })

    listPulishers.forEach((pulisher) => {
      if (pulisher.id == pulisherId) {
        pulisherObject = { ...pulisher };
        return;
      }
    })
   
    convertToBase64(file, function (error, base64String) {
      if (error) {
        alert(error);
      } else {
        console.log(base64String);
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
          moTa: description,
          hinhAnh: base64String,
        };

        createBook(formData);
        alert("Thêm thành công");
        window.location.href = "books.html";
      }
    });

    
  };
}
handleCreateForm();
