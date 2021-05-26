import React, { useState } from "react";
import { useSelector } from "react-redux";
import {
  Typography,
  Avatar,
  Divider,
  Grid,
  List,
  ListItem,
  ListItemIcon,
  ListItemText
} from "@material-ui/core";
import EmailIcon from "@material-ui/icons/Email";
import LocationOnIcon from "@material-ui/icons/LocationOn";
import CreditCardIcon from "@material-ui/icons/CreditCard";
import { useDispatch } from "react-redux";
import { useEffect } from "react";
import { getProfile, addCard, deleteCard } from "../actions/userActions";
import { Button, Modal } from "react-bootstrap";
import { addAddress, deleteAddress } from "../actions/productActions";

function Profile() {
  const dispatch = useDispatch();
  const currentUser = useSelector(state => state.currentUser);
  useEffect(() => {
    dispatch(getProfile(currentUser.userId));
  }, []);
  const profile = useSelector(state => state.profile);

  const [show, setShow] = useState(false);
  const handleClose = () => setShow(false);
  const handleShow = () => setShow(true);
  const [addressModal, setAddressModal] = useState({
    address1: "",
    address2: "",
    state: "",
    city: "",
    zip: "",
    userAccountId: currentUser.userId
  });
  const onChange = e => {
    const { name, value } = e.target;
    setAddressModal(item => ({ ...item, [name]: value }));
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

  const handleDeleteAddress = (addressId, userAccountId) => {
    dispatch(deleteAddress(addressId, userAccountId));
  };

  const [showCardModal, setShowCardModal] = useState(false);
  const handleCardModalClose = () => setShowCardModal(false);
  const handleCardModalShow = () => setShowCardModal(true);
  const [cardModal, setCardModal] = useState({
    ccNumber: "",
    holderName: "",
    expireDate: "",
    userAccountId: currentUser.userId
  });
  const onChangeCard = e => {
    const { name, value } = e.target;
    setCardModal(item => ({ ...item, [name]: value }));
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
  const handleDeleteCard = (ccId, userAccountId) => {
    dispatch(deleteCard(ccId, userAccountId));
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

      <div className="profile">
        <div className="User">
          <div
            style={{
              display: "flex",
              alignItems: "center",
              position: "relative"
            }}
          >
            <Avatar
              src="https://img.icons8.com/bubbles/2x/user-male.png"
              alt="full name"
              style={{ margin: "10px", width: "80px", height: "80px" }}
            />
            <div>
              <div>
                <Typography variant="h4" component="h2">
                  {profile?.fullName}
                </Typography>
              </div>
              <Divider />
              <div>
                <Typography>
                  <EmailIcon />
                  <a>{profile.email}</a>
                </Typography>
              </div>
            </div>
          </div>
          <Divider />
          <Typography variant="h5" component="h2" style={{ margin: "20px" }}>
            Addressess{" "}
            <button
              className="btn btn-sm btn-light border"
              onClick={handleShow}
              style={{ marginTop: "-5px" }}
            >
              <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
          </Typography>
          <Divider style={{ margin: "0 20px" }} />
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <List className="row">
                {profile?.address?.map(address => (
                  <ListItem className="col-3">
                    <ListItemIcon>
                      <LocationOnIcon />
                    </ListItemIcon>
                    <ListItemText
                      primary={address.address1 + " " + address.address2}
                      secondary={
                        address.city + "," + address.state + "," + address.zip
                      }
                    />
                    <button
                      className="ml -auto btn btn-sm mr-1"
                      style={{ marginTop: "auto" }}
                      onClick={() =>
                        handleDeleteAddress(
                          address.addressId,
                          currentUser.userId
                        )
                      }
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                    <Divider orientation="vertical" />
                  </ListItem>
                ))}
              </List>
            </Grid>
          </Grid>
          <Typography variant="h5" component="h2" style={{ margin: "20px" }}>
            Payment Cards{" "}
            <button
              className="btn btn-sm btn-light border"
              onClick={handleCardModalShow}
              style={{ marginTop: "-5px" }}
            >
              <i class="fa fa-plus" aria-hidden="true"></i>
            </button>
          </Typography>
          <Divider style={{ margin: "0 20px" }} />
          <Grid container spacing={2}>
            <Grid item xs={12}>
              <List className="row">
                {profile?.cards?.map(card => (
                  <ListItem className="col-3">
                    <ListItemIcon>
                      <CreditCardIcon />
                    </ListItemIcon>
                    <ListItemText
                      style={{ display: "inline" }}
                      primary={"Card number: " + card.ccNumber}
                      secondary={
                        <div>
                          <div>{"Holder name: " + card.holderName}</div>
                          <div>{"Valid till: " + card.expireDate}</div>
                        </div>
                      }
                    />
                    <button
                      className="ml -auto btn btn-sm mr-1"
                      style={{ marginTop: "auto" }}
                      onClick={() =>
                        handleDeleteCard(card.ccId, currentUser.userId)
                      }
                    >
                      <i class="fas fa-trash-alt"></i>
                    </button>
                    <Divider orientation="vertical" />
                  </ListItem>
                ))}
              </List>
            </Grid>
          </Grid>
        </div>
      </div>
    </>
  );
}

export default Profile;
