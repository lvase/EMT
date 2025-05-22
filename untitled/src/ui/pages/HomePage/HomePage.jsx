import React from 'react';
import {Box, Container, Typography} from "@mui/material";
import AuthorsPerCountryChart from "../../components/authors/AuthorsPerCountryChart.jsx";

const HomePage = () => {
    return (
        <Box sx={{m:0, p:0}}>
            <Container maxWidth="xl" sx={{mt:3, py: 3}}>
                <Typography variant="h4" gutterBottom>
                    Welcome to Library! 👋
                </Typography>
                <Typography variant="body1" sx={{ mb: 4 }}>
                    This is the home page.
                </Typography>
            </Container>
            <AuthorsPerCountryChart></AuthorsPerCountryChart>
        </Box>
    );
};

export default HomePage;
