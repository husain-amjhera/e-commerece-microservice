import React from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import WishListItem from "./WishListItem";

function WishList() {
  const dispatch = useDispatch();
  const currentUser = useSelector(state => state.currentUser);
  const wishList = useSelector(state => state.wishList.list);
  console.log(wishList);
  return (
    <div className="wishList" style={{ marginTop: "70px" }}>
      {wishList?.length === 0 ? (
        <div
          style={{
            display: "grid",
            objectFit: "contain",
            height: "85vh",
            alignContent: "center"
          }}
        >
          <img
            className="mx-auto"
            src="https://bestpricehvac.com.au/wp-content/themes/bestpricehvac/images/Your-wishlist-is-empty.jpg"
            alt="WishList Empty"
          />
        </div>
      ) : (
        <>
          <div className="">
            <h1>Your WishList</h1>
            <hr />
          </div>
          <div className="row mt-4">
            {wishList.map(item => (
              <div className="col-sm-3">
                <WishListItem
                  key={item.wishListItemId}
                  id={item.inventoryId}
                  name={item.title}
                  price={item.price}
                  imageUrl={item.imageUrl}
                />
              </div>
            ))}
          </div>
        </>
      )}
    </div>
  );
}

export default WishList;
