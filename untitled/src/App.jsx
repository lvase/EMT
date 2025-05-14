import React from 'react';

import {BrowserRouter, Routes, Route} from "react-router";
import Layout from "./ui/components/layouts/layout/Layout.jsx";
import HomePage from "./ui/pages/HomePage/HomePage.jsx";
import BookPage from "./ui/pages/BookPage/BookPage.jsx";
import BookDetails from "./ui/components/books/BookDetails/BookDetails.jsx";

const App = () => {
  return (
      <BrowserRouter>
        <Routes>
          <Route path="/" element={<Layout/>}>
            <Route index element={<HomePage/>}/>
            <Route path="books" element={<BookPage/>}/>
            <Route path="books/:id" element={<BookDetails/>}/>
          </Route>
        </Routes>
      </BrowserRouter>
  );
};

export default App;
