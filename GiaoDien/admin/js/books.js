var apiBooks = "http://localhost:8080/savis/book/list";

getAllBook(renderBooks);

function getAllBook(callback) {
  fetch(apiBooks)
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

function renderBooks(books) {
  var tbody = document.getElementById("info-books");

    var htmls = books.map(function (book, index) {
      var base64String = book.hinhAnh;
        var src = "data:image/png;base64," + base64String;
        var giaNhap = book.giaNhap.toLocaleString("en-US");
        var giaBan = book.giaBan.toLocaleString("en-US");
    return `
      <tr>
                <td>${index + 1}</td>
                <td>${book.ma}</td>
              <td>${book.ten}</td>
              <td>${book.realm.tenLinhVuc}</td>
              <td>${book.author.tenTacGia}</td>
              <td>${book.pulisher.tenNXB}</td>
              <td>${book.sale != null ? book.sale.giaTri : "0"}</td>
              <td>${book.trongLuong}</td>
              <td>${book.soLuong}</td>
              <td>${book.soLuongDaBan}</td>
              <td>${giaNhap}</td>
              <td>${giaBan}</td>
              <td style="text-align: center;">
                <img src="${src}" style="max-width: 60px; max-height: 60px">
              </td>
              
              <td class="moTa">${book.moTa}</td>
              <td>${book.trangThai==1?"Đang bán":"Không bán"}</td>
              <td>
                <a href="bookUpdate.html?id=${
                  book.id
                }" class="btn btn-success">Sửa</a>
              </td>
            </tr>
    `;
  });
  var html = htmls.join("");
  tbody.innerHTML = html;
truncateText();
}

function truncateText() {
  var longTextElements = document.querySelectorAll(".moTa");
  var maxLength = 100;

  longTextElements.forEach(function (element) {
    var originalText = element.innerText;

    if (originalText.length > maxLength) {
      var truncatedText = originalText.substring(0, maxLength) + "...";
      element.innerText = truncatedText;
    }
  });
}
