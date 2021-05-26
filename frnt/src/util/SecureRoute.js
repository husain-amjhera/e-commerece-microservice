import React from "react";
import { useSelector } from "react-redux";
import { Route } from "react-router-dom";
import { Redirect } from "react-router-dom";

function SecureRoute({ component: Component, ...rest }) {
  const currentUser = useSelector(state => state.currentUser);
  return currentUser.userId ? (
    <Route {...rest} component={Component} />
  ) : (
    <Redirect to={{ pathname: "/" }} />
  );
}

export default SecureRoute;
