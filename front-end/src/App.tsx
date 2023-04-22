import React from 'react';
import './App.css';
import RegisterView from "./containers/user/RegisterView";
import {BrowserRouter, Route, Routes} from "react-router-dom";
import ErrorPage from "./containers/ErrorPage";
import Header from "./containers/Header";
import Home from "./containers/Home";
import About from "./containers/About";
import Footer from "./containers/Footer";
import Login from "./containers/Login";

function App() {
  return (
      <div className="App">
        <Header/>
        <main className={"container"}>
          <BrowserRouter>
            <Routes>
              <Route path={""} Component={Home}></Route>
              <Route path={"home"} Component={Home}></Route>
              <Route path={"about"} Component={About}></Route>
              <Route path={"users/register"} Component={RegisterView}
                     errorElement={<ErrorPage/>}></Route>
              <Route path={"login"} Component={Login}></Route>
              <Route path={"*"} Component={ErrorPage}></Route>
            </Routes>
            <Footer/>
          </BrowserRouter>
        </main>

        <script
            src={"https://cdn.jsdelivr.net/npm/bootstrap@5.2.3/dist/js/bootstrap.bundle.min.js"}></script>
      </div>
  );
}

export default App;
