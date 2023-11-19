const apiBook = "http://localhost:8080/savis/book/list";

function start() {
  getBook(renderCarosuel);
  getBook(renderBook);
}

start();

function getBook(callback) {
 fetch(apiBook)
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

function renderBook(books) {
  var htmls = books.map(function (book) {
    const base64String = book.hinhAnh;
    var src = "data:image/png;base64," + base64String;
    var price = "";
    var price_sale = "";
    var sale = "";
    if (book.sale) {
      sale = book.giaBan - book.sale.giaTri;
      price = sale.toLocaleString("en-US")+"đ";
      price_sale = book.giaBan.toLocaleString("en-US")+"đ";
    } else {
      price_sale = book.giaBan.toLocaleString("en-US") + "đ";
    }
    
     
    return `
        <div class="card col-3 book_card" style="width: 250px; ">
          <div id="${book.ma}">
          <img src="${src}" class="card-img-top" alt="${book.ten}" class="image_book" width="224px" height="250px" style="object-fit: contain;">
          </div>
          <div class="card-body">
            <h5 class="card-title"><a href="detailBook.html?id=${book.id}" class="book_name" onclick="detailBook(${book.id})">${book.ten}</a></h5>
            <p class="card-text price_sale">
              ${price_sale}
              
            </p>
            <p class="card-text"><small class="text-muted"><del class="price">${price}</del></small></p>
          </div>
        </div>
      `;
  });
  var html = htmls.join("");
  document.getElementById("book").innerHTML = html;
}

function renderCarosuel(books) {
  books.sort((a, b) => {
    return b.soLuongDaBan - a.soLuongDaBan;
  });
  var length = books.length;
  var htmls = ``;

  if (length < 8) {
    for (let i = 0; i < length; i++) {
      var base64String = books[i].hinhAnh;
      var src = "data:image/png;base64," + base64String;
       var price = "";
       var price_sale = "";
       var sale = "";
       if (books[i].sale) {
         sale = books[i].giaBan - books[i].sale.giaTri;
         price = sale.toLocaleString("en-US") + "đ";
         price_sale = books[i].giaBan.toLocaleString("en-US") + "đ";
       } else {
         price_sale = books[i].giaBan.toLocaleString("en-US") + "đ";
       }
      htmls += `
        <div class="card col-3 book_card" style="width: 250px; ">
          <div id="${books[i].ma}">
          <img src="${src}" class="card-img-top" alt="${books[i].ten}" class="image_book" width="224px" height="250px" style="object-fit: contain;">
          </div>
          <div class="card-body">
            <h5 class="card-title"><a href="detailBook.html?id=${books[i].id}" class="book_name" onclick="detailBook(${books[i].id})">${books[i].ten}</a></h5>
            <p class="card-text price_sale">
              ${price_sale}
              
            </p>
            <p class="card-text"><small class="text-muted"><del class="price">${price}</del></small></p>
          </div>
        </div>
      `;
    }
  } else {
    for (let i = 0; i < 8; i++) {
      var base64String = books[i].hinhAnh;
      var src = "data:image/png;base64," + base64String;
      var price = books[i].giaBan.toLocaleString("en-US");
      var price = "";
      var price_sale = "";
      var sale = "";
      if (books[i].sale) {
        sale = books[i].giaBan - books[i].sale.giaTri;
        price = sale.toLocaleString("en-US") + "đ";
        price_sale = books[i].giaBan.toLocaleString("en-US") + "đ";
      } else {
        price_sale = books[i].giaBan.toLocaleString("en-US") + "đ";
      }
      htmls += `
        <div class="card col-3 book_card" style="width: 250px; ">
          <div id="${books[i].ma}">
          <img src="${src}" class="card-img-top" alt="${books[i].ten}" class="image_book" width="224px" height="250px" style="object-fit: contain;">
          </div>
          <div class="card-body">
            <h5 class="card-title"><a href="detailBook.html?id=${books[i].id}" class="book_name" onclick="detailBook(${books[i].id})">${books[i].ten}</a></h5>
            <p class="card-text price_sale">
              ${price_sale}
              
            </p>
            <p class="card-text"><small class="text-muted"><del class="price">${price}</del></small></p>
          </div>
        </div>
      `;
    }
  }

  // var html = htmls.join("");
  document.getElementById("recommended-books").innerHTML = htmls;
}

function detailBook(id, event) {
  event.preventDefault();
  window.location.href = "detailBook.html?id=" + id;
}
