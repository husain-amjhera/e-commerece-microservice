import React, { useState } from "react";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import {
  placeOrder,
  addAddress,
  deleteAddress,
  getShippingCharge
} from "../actions/productActions";
import { Modal, Button } from "react-bootstrap";
import { addCard, deleteCard } from "../actions/userActions";
import Subtotal from "./Subtotal";
import { GET_SHIPPING_CHARGE_LIST } from "../actions/type";
function PaymentAndAddress() {
  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [showCardModal, setShowCardModal] = useState(false);
  const handleCardModalClose = () => setShowCardModal(false);
  const handleCardModalShow = () => setShowCardModal(true);
  const history = useHistory();
  const dispatch = useDispatch();
  const basket = useSelector(state => state.cart);
  const cart = useSelector(state => state.cart);
  const cards = useSelector(state => state.card.list);
  const addresses = useSelector(state => state.address.list);
  const shippingCharge = useSelector(state => state.shippingChargeList);
  console.log(shippingCharge);
  const currentUser = useSelector(state => state.currentUser);
  const [address, setAddress] = useState(0);
  const [card, setCard] = useState(0);
  const [addressModal, setAddressModal] = useState({
    address1: "",
    address2: "",
    state: "",
    city: "",
    zip: "",
    userAccountId: currentUser.userId
  });
  const [cardModal, setCardModal] = useState({
    ccNumber: "",
    holderName: "",
    expireDate: "",
    userAccountId: currentUser.userId
  });
  const onChange = e => {
    const { name, value } = e.target;
    setAddressModal(item => ({ ...item, [name]: value }));
  };

  const onChangeCard = e => {
    const { name, value } = e.target;
    setCardModal(item => ({ ...item, [name]: value }));
  };
  const handleDeleteAddress = (addressId, userAccountId) => {
    dispatch(deleteAddress(addressId, userAccountId));
  };

  const handleDeleteCard = (ccId, userAccountId) => {
    dispatch(deleteCard(ccId, userAccountId));
  };
  const handleCardSubmit = event => {
    event.preventDefault();
    setCardModal(item => ({ ...item, userAccountId: currentUser.userId }));
    if (cardModal.userAccountId !== null) {
      dispatch(addCard(cardModal));
      handleCardModalClose();
      setCardModal({
        ccNumber: "",
        holderName: "",
        expireDate: "",
        userAccountId: currentUser.userId
      });
    }
  };
  const handleSubmit = event => {
    event.preventDefault();
    setAddressModal(item => ({ ...item, userAccountId: currentUser.userId }));
    dispatch(addAddress(addressModal));
    handleClose();
    setAddressModal({
      address1: "",
      address2: "",
      state: "",
      city: "",
      zip: "",
      userAccountId: currentUser.userId
    });
  };

  const handlePlaceOrder = () => {
    if (cart?.cartDetails.length > 0) {
      dispatch(
        placeOrder(
          {
            shoppingCartId: currentUser.shoppingCartId,
            userAccountId: currentUser.userId,
            addressId: address,
            creditCardId: card
          },
          history
        )
      );
      dispatch({
        type: GET_SHIPPING_CHARGE_LIST,
        payload: {}
      });
    }
  };

  const [shippingCharges, setShippingCharges] = useState(0);
  const handleShippingCharges = () => {
    let charge = shippingCharge?.charge;
    setShippingCharges(charge);
  };

  return (
    <>
      <Modal show={show} onHide={handleClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Address</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form onSubmit={handleSubmit}>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                name="address1"
                placeholder="Address 1"
                value={addressModal.address1}
                onChange={onChange}
                required
              />
            </div>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                name="address2"
                placeholder="Address 2"
                value={addressModal.address2}
                onChange={onChange}
                required
              />
            </div>
            <div class="form-row">
              <div class="form-group col-md-6">
                <input
                  type="text"
                  class="form-control"
                  name="city"
                  placeholder="City"
                  value={addressModal.city}
                  onChange={onChange}
                  required
                />
              </div>
              <div class="form-group col-md-4">
                <input
                  type="text"
                  class="form-control"
                  name="state"
                  placeholder="State"
                  value={addressModal.state}
                  onChange={onChange}
                  required
                />
              </div>
              <div class="form-group col-md-2">
                <input
                  type="text"
                  class="form-control"
                  name="zip"
                  pattern="[0-9]{6}"
                  placeholder="Zip"
                  value={addressModal.zip}
                  onChange={onChange}
                  required
                />
              </div>
            </div>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleClose}>
                Close
              </Button>
              <Button variant="primary" type="submit">
                Save
              </Button>
            </Modal.Footer>
          </form>
        </Modal.Body>
      </Modal>

      <Modal show={showCardModal} onHide={handleCardModalClose}>
        <Modal.Header closeButton>
          <Modal.Title>Add Card</Modal.Title>
        </Modal.Header>
        <Modal.Body>
          <form onSubmit={handleCardSubmit}>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                pattern="[0-9]{16}"
                name="ccNumber"
                placeholder="Credit card number"
                value={cardModal.ccNumber}
                onChange={onChangeCard}
                required
              />
            </div>
            <div class="form-group">
              <input
                type="text"
                class="form-control"
                pattern="[A-Za-z].{2,30}"
                name="holderName"
                placeholder="Holder Name"
                value={cardModal.holderName}
                onChange={onChangeCard}
                required
              />
            </div>
            <div class="form-group">
              <input
                type="date"
                class="form-control"
                name="expireDate"
                placeholder="Valid till"
                value={cardModal.expireDate}
                onChange={onChangeCard}
                required
              />
            </div>
            <Modal.Footer>
              <Button variant="secondary" onClick={handleCardModalClose}>
                Close
              </Button>
              <Button variant="primary" type="submit">
                Save
              </Button>
            </Modal.Footer>
          </form>
        </Modal.Body>
      </Modal>

      <div className="row">
        <div className="col-8">
          {" "}
          <div
            className="PaymentAndAddress p-3"
            style={{ border: "1px solid grey", marginTop: "20px" }}
          >
            <legend>
              Addresses{" "}
              <button
                className="btn btn-sm btn-light border"
                onClick={handleShow}
                style={{ marginTop: "-5px" }}
              >
                <i class="fa fa-plus" aria-hidden="true"></i>
              </button>
            </legend>
            <hr />
            <div className="row mb-3">
              {addresses.map(item => (
                <div
                  class="col-5 mx-4 mb-2"
                  style={{
                    border: "0.5px solid grey",
                    borderRadius: "10px",
                    backgroundColor: "white"
                  }}
                >
                  <label style={{ width: "100%", height: "70%" }}>
                    <div className="row">
                      <input
                        type="radio"
                        className="mt-2 ml-2"
                        name="address"
                        onChange={() => {
                          dispatch(
                            getShippingCharge({
                              name: item.state.trim().toLowerCase()
                            })
                          );
                          setAddress(item.addressId);
                          handleShippingCharges();
                        }}
                      />
                    </div>
                    <div class="card-body" style={{ marginTop: "-30px" }}>
                      <p className="mb-0">
                        <strong>Address: </strong>
                        {item.address1} ,{item.address2}
                      </p>
                      <p className="mb-0">
                        <strong>State:</strong> {item.state}{" "}
                      </p>
                      <p className="mb-0">
                        <strong> City: </strong>
                        {item.city}{" "}
                      </p>
                      <p className="mb-0">
                        <strong>Zip: </strong>
                        {item.zip}
                      </p>
                    </div>
                  </label>
                  <div className="row">
                    <button
                      className="btn btn-light btn-sm"
                      style={{ marginLeft: "auto", marginRight: "2%" }}
                      onClick={() =>
                        handleDeleteAddress(item.addressId, currentUser.userId)
                      }
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                  </div>
                </div>
              ))}
            </div>

            <legend>
              Cards{" "}
              <button
                className="btn btn-sm btn-light border"
                onClick={handleCardModalShow}
                style={{ marginTop: "-5px" }}
              >
                <i class="fa fa-plus" aria-hidden="true"></i>
              </button>
            </legend>
            <hr />
            <div className="row" style={{ overflowY: "auto" }}>
              {cards.map(item => (
                <div
                  className="col-5 mx-4 mb-3"
                  style={{
                    border: "0.5px solid grey",
                    borderRadius: "10px",
                    backgroundColor: "white"
                  }}
                >
                  <label>
                    <input
                      type="radio"
                      className="mt-2 mr-3"
                      name="card"
                      onChange={e => setCard(item.ccId)}
                    />
                    <div class="card-body" style={{ marginTop: "-30px" }}>
                      <p className="mb-0">
                        <strong>Card Number: </strong>
                        {item.ccNumber}
                      </p>
                      <p className="mb-0">
                        <strong>Holder Name: </strong> {item.holderName}{" "}
                      </p>
                      <p className="mb-0">
                        <strong> Valid Till: </strong>
                        {item.expireDate}{" "}
                      </p>
                    </div>
                  </label>
                  <button
                    className="ml -auto btn btn-light btn-sm"
                    style={{
                      marginTop: "20%",
                      float: "right"
                    }}
                    onClick={() =>
                      handleDeleteCard(item.ccId, currentUser.userId)
                    }
                  >
                    <i class="fas fa-trash-alt"></i>
                  </button>
                </div>
              ))}
            </div>
          </div>
        </div>
        <div className="col-4 mt-5">
          <Subtotal
            cartItemList={basket.cartDetails}
            shippingCharges={
              shippingCharge?.charge === undefined ? 0 : shippingCharge?.charge
            }
            ship
          />
          <button
            style={{
              alignSelf: "center",
              background: "#f0c14b",
              borderRadius: "2px",
              width: "95%",
              height: "30px",
              border: "1px solid",
              marginTop: "-10px",
              borderColor: " #a88734 #9c7e31 #846a29",
              color: "#111"
            }}
            onClick={handlePlaceOrder}
            disabled={!(address && card)}
          >
            Place order
          </button>
        </div>
      </div>
    </>
  );
}

export default PaymentAndAddress;
