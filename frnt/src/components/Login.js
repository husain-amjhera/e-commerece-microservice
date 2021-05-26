import React, { useState } from "react";
import "./Login.css";
import { useDispatch } from "react-redux";
import { useHistory } from "react-router-dom";
import { loginUser } from "../actions/userActions";
import { Link } from "react-router-dom";
function Login() {
  const history = useHistory();
  const dispatch = useDispatch();
  const [loginRequest, setLoginRequest] = useState({
    email: "",
    password: ""
  });
  const onChange = e => {
    const { name, value } = e.target;
    setLoginRequest(content => ({ ...content, [name]: value }));
  };

  const handleSubmit = event => {
    event.preventDefault();
    dispatch(loginUser(loginRequest, history));
  };

  return (
    <div className="login">
      <div className="simple-login-container">
        <h1>Login</h1>
        <form onSubmit={handleSubmit}>
          <div className="row">
            <div className="col-md-12 form-group">
              <input
                name="email"
                type="email"
                className="form-control"
                placeholder="Email"
                onChange={onChange}
                required
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <input
                name="password"
                type="password"
                placeholder="Password"
                className="form-control"
                onChange={onChange}
                required
              />
            </div>
          </div>
          <div className="row">
            <div className="col-md-12 form-group">
              <button type="submit" className="btn btn-block btn-login">
                Login
              </button>
            </div>
          </div>
          <div className="row">
            <Link to="/register" className="ml-3 signUp">
              <h6>Not a member, SignUp</h6>
            </Link>
          </div>
        </form>
      </div>
    </div>
  );
}

export default Login;
