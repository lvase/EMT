
import React, { useState } from 'react';
import { Box, Button, CircularProgress } from '@mui/material';
import BooksGrid from '../../components/books/BookGrid/BookGrid.jsx';
import useBooks from '../../../hooks/useBooks.js';
import AddBookDialog from '../../components/books/AddBookDialog/AddBookDialog.jsx';
import './BookPage.css';

const BooksPage = () => {
    const { books, loading, onAdd, onEdit, onDelete } = useBooks();
    const [addDialogOpen, setAddDialogOpen] = useState(false);

    return (
        <>
            <Box className="books-box">
                {loading ? (
                    <Box className="progress-box">
                        <CircularProgress />
                    </Box>
                ) : (
                    <>
                        <Box sx={{ display: 'flex', justifyContent: 'flex-end', mb: 2 }}>
                            <Button
                                variant="contained"
                                color="primary"
                                onClick={() => setAddDialogOpen(true)}
                            >
                                Add Book
                            </Button>
                        </Box>
                        <BooksGrid
                            books={books}
                            onEdit={onEdit}
                            onDelete={onDelete}
                        />
                    </>
                )}
            </Box>
            <AddBookDialog
                open={addDialogOpen}
                onClose={() => setAddDialogOpen(false)}
                onAdd={onAdd}
            />
        </>
    );
};

export default BooksPage;
