import React, { useState } from 'react';
import InfoIcon from '@mui/icons-material/Info';
import EditIcon from '@mui/icons-material/Edit';
import DeleteIcon from '@mui/icons-material/Delete';
import { Box, Button, Card, CardActions, CardContent, Typography } from '@mui/material';
import { useNavigate } from 'react-router-dom';
import EditBookDialog from '../../books/EditBookDialog/EditBookDialog.jsx';
import DeleteBookDialog from '../../books/DeleteBookDialog/DeleteBookDialog.jsx';

const BookCard = ({ book, onEdit, onDelete }) => {
    const navigate = useNavigate();
    const [editDialogOpen, setEditDialogOpen] = useState(false);
    const [deleteDialogOpen, setDeleteDialogOpen] = useState(false);

    return (
        <>
            <Card sx={{ boxShadow: 3, borderRadius: 2, p: 1 }}>
                <CardContent>
                    <Typography variant="h5">{book.name}</Typography>
                    <Typography variant="subtitle2">Category: {book.category}</Typography>
                    <Typography variant="body2">Author ID: {book.author}</Typography>
                    <Typography variant="body1" sx={{ mt: 1 }}>
                        Available: {book.availableCopies}
                    </Typography>
                    <Typography variant="body2">Times Rented: {book.timesRented}</Typography>
                </CardContent>
                <CardActions sx={{ justifyContent: 'space-between' }}>
                    <Button
                        size="small"
                        color="info"
                        startIcon={<InfoIcon />}
                        onClick={() => navigate(`/books/${book.id}`)}
                    >
                        Info
                    </Button>
                    <Box>
                        <Button
                            size="small"
                            color="warning"
                            startIcon={<EditIcon />}
                            sx={{ mr: 0.5 }}
                            onClick={() => setEditDialogOpen(true)}
                        >
                            Edit
                        </Button>
                        <Button
                            size="small"
                            color="error"
                            startIcon={<DeleteIcon />}
                            onClick={() => setDeleteDialogOpen(true)}
                        >
                            Delete
                        </Button>
                    </Box>
                </CardActions>
            </Card>

            <EditBookDialog
                open={editDialogOpen}
                onClose={() => setEditDialogOpen(false)}
                book={book}
                onEdit={onEdit}
            />

            <DeleteBookDialog
                open={deleteDialogOpen}
                onClose={() => setDeleteDialogOpen(false)}
                book={book}
                onDelete={onDelete}
            />
        </>
    );
};

export default BookCard;