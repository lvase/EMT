import React from 'react';
import { Grid } from '@mui/material';
import BookCard from '../BookCard/BookCard.jsx';

const BookGrid = ({ books, onEdit, onDelete }) => (
    <Grid container spacing={{ xs: 2, md: 3 }}>
        {books.map((book) => (
            <Grid key={book.id} item xs={12} sm={6} md={4} lg={3}>
                <BookCard book={book} onEdit={onEdit} onDelete={onDelete} />
            </Grid>
        ))}
    </Grid>
);

export default BookGrid;