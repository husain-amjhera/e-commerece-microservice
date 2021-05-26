import React from "react";
import { Navbar, Nav } from "react-bootstrap";
import { Link } from "react-router-dom";
import { useSelector } from "react-redux";
import { useDispatch } from "react-redux";
import { SET_CURRENT_USER } from "../actions/type";
import { Badge } from "@material-ui/core";
import ShoppingCartIcon from "@material-ui/icons/ShoppingCart";
import PropTypes from "prop-types";
import { withStyles } from "@material-ui/core/styles";

const styles = theme => ({
  customBadge: {
    backgroundColor: "#f0c14b",
    color: "black"
  }
});
function Header(props) {
  const { classes } = props;
  const dispatch = useDispatch();
  const currentUser = useSelector(state => state.currentUser);
  const cart = useSelector(state => state.cart);
  const wishList = useSelector(state => state.wishList.list);
  let items = 0;
  cart?.cartDetails?.map(item => (items = items + item.quantity));
  const handleLogout = () => {
    dispatch({
      type: SET_CURRENT_USER,
      payload: {}
    });
  };
  return (
    <div className="header" style={{ marginBottom: "50px" }}>
      <Navbar collapseOnSelect expand="lg" bg="dark" variant="dark" fixed="top">
        <Navbar.Brand>Ecommerce</Navbar.Brand>
        <Navbar.Toggle aria-controls="responsive-navbar-nav" />
        <Navbar.Collapse id="responsive-navbar-nav">
          {currentUser.userId ? (
            <>
              <Nav className="ml-auto">
                <Nav.Link>
                  <Link className="text-white text-decoration-none" to="/home">
                    Home
                  </Link>
                </Nav.Link>

                <Nav.Link>
                  <Link className="text-white text-decoration-none" to="/cart">
                    <Badge
                      badgeContent={items}
                      classes={{ badge: classes.customBadge }}
                    >
                      <ShoppingCartIcon />
                    </Badge>
                  </Link>
                </Nav.Link>

                <Nav.Link>
                  <Link
                    className="text-white text-decoration-none"
                    to="/orders"
                  >
                    Orders
                  </Link>
                </Nav.Link>

                <Nav.Link>
                  <Link
                    className="text-white text-decoration-none"
                    to="/profile"
                  >
                    Profile
                  </Link>
                </Nav.Link>
                <Nav.Link>
                  <Link
                    className="text-white text-decoration-none"
                    to="/wishList"
                  >
                    <Badge
                      badgeContent={wishList.length}
                      classes={{ badge: classes.customBadge }}
                    >
                      Wishlist
                    </Badge>
                  </Link>
                </Nav.Link>
              </Nav>
              <Nav.Link
                className="text-white text-decoration-none"
                onClick={handleLogout}
              >
                Logout
              </Nav.Link>
            </>
          ) : (
            <Nav className="ml-auto">
              <Nav.Link>
                <Link className="text-white text-decoration-none" to="/">
                  Login
                </Link>
              </Nav.Link>

              <Nav.Link>
                <Link
                  className="text-white text-decoration-none"
                  to="/register"
                >
                  SignUp
                </Link>
              </Nav.Link>
            </Nav>
          )}
        </Navbar.Collapse>
      </Navbar>
    </div>
  );
}

Header.propTypes = {
  classes: PropTypes.object.isRequired
};

export default withStyles(styles)(Header);
