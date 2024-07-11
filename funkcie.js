function openPopup(imgSrc) {
    document.getElementById("popup-img").src = imgSrc;
    document.querySelector(".popup").style.display = "flex";
}

function closePopup() {
    document.querySelector(".popup").style.display = "none";
}

//add to card functions
let shopItemsData = [
  {
    id: "okul",
    name: "Thug Life",
    price: "15",
    desc: "Okuliare Thug Life <br> Size: M",
    img: "fotky/okuliare.jpg",
  },
];

let shop = document.getElementById("shop");

let basket = JSON.parse(localStorage.getItem("data")) || [];


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

  update(selectedItem.id);
  localStorage.setItem("data", JSON.stringify(basket));
};

let calculation = () => {
  let cartIcon = document.getElementById("cartAmount");
  cartIcon.innerHTML = basket.map((x) => x.item).reduce((x, y) => x + y, 0);
};

calculation();

let update = (id) => {
  let search = basket.find((x) => x.id === id);
  document.getElementById(id).innerHTML = search.item;
  calculation();
};


let generateShop = () => {
    return (shop.innerHTML = shopItemsData
      .map((x) => {
        let { id, name, price, desc, img } = x;
        let search = basket.find((x) => x.id === id) || [];
        return `
        <div class="item" id="product-id-${id}">
          <img class="okuliare" src=${img} alt="Okuliare"> 
              <div style="font-size: 0px" id=${id}></div>
              <div class="add">
                  <button onclick="increment(${id})"><i class="fa fa-shopping-cart fa-2xl"></i></button>
              </div>
              <b><div class="cena">${price} €</div></b> 
      </div>
      `;
      })
      .join(""));

  };
generateShop();


