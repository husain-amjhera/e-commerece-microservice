import React from "react";
import "./Subtotal.css";
import CurrencyFormat from "react-currency-format";
import { useHistory } from "react-router-dom";
import { height } from "@material-ui/system";
function Subtotal({ cartItemList, shippingCharges, ship }) {
  const history = useHistory();
  let total = 0;
  let taxTotal = 0;
  cartItemList.map(item => {
    total += item.price * item.quantity;
    taxTotal += item.price * item.quantity * (item.tax / 100);
    // return total;
  });

  let items = 0;
  cartItemList?.map(item => (items = items + item.quantity));
  const roundToTwo = num => {
    return +(Math.round(num + "e+2") + "e-2");
  };
  return (
    <div className="subtotal" style={ship ? { height: "220px" } : null}>
      {/* Price */}
      <CurrencyFormat
        renderText={value => (
          <div>
            <p className="subtotal__text">
              Subtotal ({items} items):{" "}
              <strong
                style={{
                  float: "right"
                }}
              >
                {value}
              </strong>
            </p>
            <p className="subtotal__text">
              Tax Total :
              <strong
                style={{
                  float: "right"
                }}
              >
                ₹ {roundToTwo(taxTotal)}
              </strong>
            </p>
            {ship && (
              <p className="subtotal__text">
                Shipping Charges :
                <strong
                  style={{
                    float: "right"
                  }}
                >
                  ₹ {roundToTwo(shippingCharges)}
                </strong>
              </p>
            )}
            <p className="subtotal__text">
              Grand Total :
              <strong
                style={{
                  float: "right"
                }}
              >
                ₹ {roundToTwo(total + taxTotal + (ship ? shippingCharges : 0))}
              </strong>
            </p>
          </div>
        )}
        decimalScale={2}
        value={roundToTwo(total)}
        displayType={"text"}
        thousandSeperator={true}
        prefix={"₹ "}
      />
      {ship ? null : (
        <button onClick={e => history.push("/payment")}>
          Proceed to Checkout
        </button>
      )}
    </div>
  );
}

export default Subtotal;
