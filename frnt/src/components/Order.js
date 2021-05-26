import React from "react";
import { useEffect } from "react";
import { useDispatch } from "react-redux";
import { useSelector } from "react-redux";
import { getOrders } from "../actions/productActions";
import Accordion from "@material-ui/core/Accordion";
import AccordionSummary from "@material-ui/core/AccordionSummary";
import AccordionDetails from "@material-ui/core/AccordionDetails";
import {
  Grid,
  Typography,
  Box,
  List,
  ListItem,
  Divider
} from "@material-ui/core";
import { Link } from "react-router-dom";

function Order() {
  const dispatch = useDispatch();
  const orders = useSelector(state => state.orders);
  const currentUser = useSelector(state => state.currentUser);

  useEffect(() => {
    dispatch(getOrders(currentUser?.userId));
  }, []);
  return (
    <div className="order" style={{ marginTop: "70px" }}>
      <div className="">
        <h1>Your Orders</h1>
        <hr />
      </div>
      {orders?.length === 0 ? (
        <div>
          <div className="ml-auto mr-auto">You have not placed any orders </div>
        </div>
      ) : (
        <div>
          {orders.map(order => (
            <Accordion>
              <AccordionSummary>
                <List
                  style={{
                    display: "flex",
                    flexDirection: "row",
                    padding: 0,
                    marginLeft: "50px",
                    width: "100vw",
                    fontSize: "18px",
                    fontWeight: "500"
                  }}
                >
                  <ListItem>Order# {order.orderId}</ListItem>
                  <ListItem>Total: ₹{order.totalAmount}</ListItem>
                  <ListItem>
                    Status:{" "}
                    <span style={{ color: "green" }}>{order.state}</span>
                  </ListItem>
                  <ListItem>Date: {order.orderDate}</ListItem>
                  <ListItem>
                    <Link to={`/invoice/${order.orderId}`}>Invoice</Link>
                  </ListItem>
                </List>
              </AccordionSummary>
              <AccordionDetails>
                <div
                  className="card m-2"
                  style={{ overflowY: "auto", overflowX: "hidden" }}
                >
                  <div className="card-body">
                    <Grid>
                      <List
                        className="row"
                        style={{
                          display: "flex",
                          flexDirection: "row",
                          padding: 0,
                          width: "100vw"
                        }}
                      >
                        {order.orderItems.map(orderDetail => (
                          <>
                            <ListItem className="col-4">
                              <img
                                style={{ objectFit: "contain" }}
                                className="mr-5"
                                src={orderDetail.imageUrl}
                                height="120px"
                                width="120px"
                                alt={orderDetail.title}
                              />
                              <div className="media-body">
                                <h5 className="mt-0">{orderDetail.title}</h5>
                                <Typography>
                                  Quantity: {orderDetail.quantity}
                                </Typography>
                                <p>Price: {orderDetail.price}</p>
                              </div>
                              <Divider flexItem={true} orientation="vertical" />
                            </ListItem>
                          </>
                        ))}
                      </List>
                    </Grid>
                    <div>
                      {/* 
                        <span
                        style={{ display: "inline-block", marginTop: "20px" }}
                      >
                        {" "}
                        <strong>Status:</strong>{" "}
                        <span style={{ color: "green", fontWeight: "500" }}>
                          {order.state}
                        </span>
                      </span>
                       */}
                      {/*
                     <div
                        className="btn btn-lg btn-warning"
                        style={{ pointerEvents: "none", float: "right" }}
                      >
                        Total :₹{order.totalAmount}
                      </div> */}
                    </div>
                  </div>
                </div>
              </AccordionDetails>
            </Accordion>
          ))}
        </div>
      )}
    </div>
  );
}

export default Order;
