import React from 'react';
import { useNavigate, useParams } from 'react-router-dom';
import useBooks from '../../../../hooks/useBooks.js';
import {
    Box,
    Button,
    Chip,
    CircularProgress,
    Grid,
    Typography,
    Paper,
    Breadcrumbs,
    Link
} from '@mui/material';
import { ArrowBack } from '@mui/icons-material';

const BookDetails = () => {
    const navigate = useNavigate();
    const { id } = useParams();
    const { book, author } = useBooks(id);

    if (!book || !author) {
        return (
            <Box sx={{ display: 'flex', justifyContent: 'center', minHeight: '60vh' }}>
                <CircularProgress />
            </Box>
        );
    }

    return (
        <Box>
            <Breadcrumbs aria-label="breadcrumb" sx={{ mb: 3 }}>
                <Link
                    underline="hover"
                    color="inherit"
                    href="#"
                    onClick={(e) => {
                        e.preventDefault();
                        navigate('/books');
                    }}
                >
                    Books
                </Link>
                <Typography color="text.primary">{book.name}</Typography>
            </Breadcrumbs>

            <Paper elevation={2} sx={{ p: 4, borderRadius: 4 }}>
                <Grid container spacing={4}>
                    <Grid item xs={12} md={3}>
                        <Box sx={{ mb: 2 }}>
                            <Chip label={`Category: ${book.category}`} />
                        </Box>
                        <Box>
                            <Typography variant="subtitle1">Author: {author.name}</Typography>
                        </Box>
                    </Grid>
                    <Grid item xs={12} md={9}>
                        <Typography variant="h4" gutterBottom>
                            {book.name}
                        </Typography>
                        <Typography variant="body1" sx={{ mb: 2 }}>
                            Available Copies: {book.availableCopies}
                        </Typography>
                        <Typography variant="body2">
                            Times Rented: {book.timesRented}
                        </Typography>
                        <Box sx={{ mt: 3 }}>
                            <Button
                                variant="outlined"
                                startIcon={<ArrowBack />}
                                onClick={() => navigate('/books')}
                            >
                                Back to List
                            </Button>
                        </Box>
                    </Grid>
                </Grid>
            </Paper>
        </Box>
    );
};

export default BookDetails;