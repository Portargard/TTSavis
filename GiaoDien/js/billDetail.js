const apiBill = "http://localhost:8080/savis/bill";
const apiBillDetail = "http://localhost:8080/savis/bill-detail";

var customerId = window.localStorage.getItem("customerId");

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
getBill(getAllBillDetail);

function getAllBillDetail(bill) {
  var api = apiBillDetail + "/bill/" + bill.id;
  document.getElementById("billCode").innerText = bill.ma;
  fetch(api)
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
    .then((billDetails) => {
      var tbody = document.getElementById("info-bill");
        var tongTiens = 0;
        var htmls = billDetails.map(function (billDetail) {
            var tongTien =billDetail.tongTien;
            tongTiens += tongTien;
          var donGia = tongTien / parseInt(billDetail.soLuong);
        return `
      <tr>
                <td>${billDetail.book.ten}</td>
                <td>${billDetail.soLuong}</td>
                <td>${donGia}</td>
                <td>${tongTien}</td>
              
            </tr>
    `;
      });
      var html = htmls.join("");
        tbody.innerHTML = html;
        document.getElementById("tongTien").innerHTML = 'Tổng tiền: ' + tongTiens.toLocaleString() + 'đ';
        
        document.getElementById("thanhToan").onclick = () => {
            var data = {
                soTienThanhToan: tongTiens
            }
            var options = {
              method: "PUT",
              headers: {
                "Content-type": "application/json; charset=UTF-8",
              },
              body: JSON.stringify(data),
            };
            fetch(apiBill + "/" + bill.id, options)
              .then((response) => {
                if (!response.ok) {
                  throw new Error("Network response was not ok");
                }
                return response.json();
              }).then(data => {
                  alert("Thanh toán thành công");
                  window.location.href = "TrangChu.html";
                  return data;
              })
              .catch((error) => {
                console.error(
                  "There was a problem with the fetch operation:",
                  error
                );
              });
        }
    })
    .catch((error) => {
      console.error("There was a problem with the fetch operation:", error);
      return null;
    });
}
