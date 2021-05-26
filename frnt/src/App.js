import "bootstrap/dist/css/bootstrap.css";
import "./App.css";
import { BrowserRouter as Router, Route } from "react-router-dom";
import Home from "./components/Home";
import { getProducts, getShippingChargeList } from "./actions/productActions";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import Login from "./components/Login";
import Header from "./components/Header";
import Register from "./components/Register";
import Cart from "./components/Cart";
import SecureRoute from "./util/SecureRoute";
import PaymentAndAddress from "./components/PaymentAndAddress";
import Order from "./components/Order";
import Profile from "./components/Profile";
import WishList from "./components/WishList";
import Invoice from "./components/Invoice";

function App() {
  const dispatch = useDispatch();
  useEffect(() => {
    dispatch(getProducts(0));
    // dispatch(getShippingChargeList());
  }, [dispatch]);
  return (
    <div className="app m-3">
      <Router>
        <Header />
        <Route path="/" exact>
          <Login />
        </Route>
        <Route path="/register" exact component={Register} />
        <SecureRoute path="/home" component={Home} />
        <SecureRoute path="/orders" component={Order} />
        <SecureRoute path="/cart" component={Cart} />
        <SecureRoute path="/payment" component={PaymentAndAddress} />
        <SecureRoute path="/profile" component={Profile} />
        <SecureRoute path="/wishList" component={WishList} />
        <SecureRoute path="/invoice/:orderId" component={Invoice} />
      </Router>
    </div>
  );
}
export default App;
