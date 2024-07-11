let label = document.getElementById("label");
let ShoppingCart = document.getElementById("shopping-cart");

let basket = JSON.parse(localStorage.getItem("data")) || [];

let shopItemsData = [
    {
      id: "okul",
      name: "Thug Life",
      price: "15",
      desc: "Okuliare Thug Life <br> Size: M",
      img: "fotky/okuliare.jpg",
    },
];

let calculation = () => {
  let cartIcon = document.getElementById("cartAmount");
  cartIcon.innerHTML = basket.map((x) => x.item).reduce((x, y) => x + y, 0);
};

calculation();
let cislo = 1;
let generateCartItems = () => {
  if (basket.length !== 0) {
    return (ShoppingCart.innerHTML = basket
      .map((x) => {
        let { id, item } = x;
        let search = shopItemsData.find((y) => y.id === id) || [];
        return `
        <form action="cart-udaje.html">
        <table style="width: auto;">
          <tbody>
            <tr>
              <td><img width="100px" src=${search.img} alt=""></td>
              <td style="width: 120px;"><p>${search.name}</p></td>
              <td style="width: 70px;"><p>$ ${search.price}</p></td>
              <td class="increments1"><i onclick="decrement(${id})" class="bi bi-dash-lg"></i></td>
              <td><div id=${id} class="quantity">${item}</div></td>
              <td class="increments2"><i onclick="increment(${id})" class="bi bi-plus-lg"></i></td>
              <td style="width: 90px;">$ ${item * search.price}</td>
              <td style="width: 50px; color: red;"><i onclick="removeItem(${id})" class="bi bi-x-lg"></i></td>
            </tr>
            <tr style="background-color: grey;"><td style="padding: 2px;" colspan="8"></td></tr>
            <tr><td colspan="8"><div class="butcart"><button class="btn btn-success">Prejsť do pokladne</button></div></tr></td>
          </tbody>
        </table>
        </form>
        `;
      })
      .join(""));
  } else {
    ShoppingCart.innerHTML = ``;
    label.innerHTML = `
    <br>
    <h2>Košík je prázdny</h2>
    <a href="shop.html">
      <button>Spať do obchodu</button>
    </a> 
    <p style="background-color: grey; padding: 2px; margin-top:10px"></p>

    `;
  }
};

generateCartItems();

let increment = (id) => {
  let selectedItem = id;
  let search = basket.find((x) => x.id === selectedItem.id);

  if (search === undefined) {
    basket.push({
      id: selectedItem.id,
      item: 1,
    });
  } else {
    search.item += 1;
  }

  generateCartItems();
  update(selectedItem.id);
  localStorage.setItem("data", JSON.stringify(basket));
};
let decrement = (id) => {
  let selectedItem = id;
  let search = basket.find((x) => x.id === selectedItem.id);

  if (search === undefined) return;
  else if (search.item === 0) return;
  else {
    search.item -= 1;
  }
  update(selectedItem.id);
  basket = basket.filter((x) => x.item !== 0);
  generateCartItems();
  localStorage.setItem("data", JSON.stringify(basket));
};

let update = (id) => {
  let search = basket.find((x) => x.id === id);
  // console.log(search.item);
  document.getElementById(id).innerHTML = search.item;
  calculation();
  TotalAmount();
};

let removeItem = (id) => {
  let selectedItem = id;
  // console.log(selectedItem.id);
  basket = basket.filter((x) => x.id !== selectedItem.id);
  generateCartItems();
  TotalAmount();
  localStorage.setItem("data", JSON.stringify(basket));
};

let clearCart = () => {
  basket = [];
  generateCartItems();
  localStorage.setItem("data", JSON.stringify(basket));
};


let cupon = () => {
  let kupmeno = document.getElementById("kupon").value;
  if (kupmeno.toUpperCase() === "KUPON20") {
    return 0.8;
  } else {
    return 1;
  }
};

let zaver = () => {
  if (basket.length !== 0) {
    odoslat.innerHTML = `
    <div class="posun">
      <b>Vložťe kupón:</b>
      <form id="kuponForm" class="kupon-form">
        <input id="kupon" type="text" style="height: 30px;">
        <button class="kupon-poslat"><i class="gg-mail-forward"></i></button>
        </form>
    </div>
    `;
    document.getElementById("kuponForm").addEventListener("submit", function (event) {
      event.preventDefault();
      TotalAmount();
      
    });

  } else return;
};
zaver();

let TotalAmount = () => {
  cupon();
  if (basket.length !== 0) {
    let amount = basket
      .map((x) => {
        let { item, id } = x;
        let search = shopItemsData.find((y) => y.id === id) || [];
        return item * search.price;
      })
      .reduce((x, y) => x + y, 0);
    label.innerHTML = `
    <div class="spcena"><h4>Cena spolu : $ ${amount*cupon()}</h4></div>
     `; 
     if (cupon() !== 1) {
      label.innerHTML += `<div class="uscena"><h6>Ušetrili ste: $ ${(amount*(1-cupon())).toFixed(0)}</h6></div>`;
     }
  } else return;
};
TotalAmount();