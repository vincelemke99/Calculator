let isResultState = false;  // Track whether the current display is a result
window.onload = function() {
    var resultArea = document.getElementById('resultArea');  // Display area for calculations
    var buttons = Array.from(document.getElementById('buttonContainer').getElementsByTagName('button'));  // Get all buttons

    buttons.forEach(function(button) {
        button.addEventListener('click', function() {  // Add event listener for each button
            var content = this.textContent;  // Get button content

            if (content === 'AC') {  // Clear all
                resultArea.textContent = '0';
            } else if (content === 'DEL' && isResultState ) {  // Delete last character
                resultArea.textContent = resultArea.textContent.slice(0, -1) || '0';
            } else if (content === '=') {  // Calculate the result
                sendCalculation();  // Send calculation to the backend
            } else if (content === '+/-') {  // Toggle the sign
                var currentValue = parseFloat(resultArea.textContent);
                resultArea.textContent = (currentValue * -1).toString();  // Change the sign
            } else if (content === 'x^y') {  // Handle exponentiation
                resultArea.textContent += '^';  // Add the caret for exponentiation
            } else if (content === '÷') {  // Handle exponentiation
                resultArea.textContent += '/';  // Add the caret for exponentiation
            }else if (content === 'x') {  // Handle exponentiation
                resultArea.textContent += '*';  // Add the caret for exponentiation
            } else {  // Append content to the result area
                if (resultArea.textContent === '0') {
                    resultArea.textContent = content;
                } else {
                    resultArea.textContent += content;
                }
            }
        });
    });
};

// Function to send the current calculation to the backend
function sendCalculation() {
    var resultArea = document.getElementById('resultArea');
    var input = resultArea.textContent;  // Get the current expression

    fetch('http://localhost:8080/calculation/add', {  // Ensure the endpoint is correct
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({ input: input }),  // Send the expression to the backend
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();  // Parse the response
        })
        .then(data => {
            var output = parseFloat(data.output);  // Convert to a number
            // If the output is an integer, remove the decimal part
            resultArea.textContent = Number.isInteger(output) ? output.toString() : data.output;  // Format the result
        })
        .catch(error => {
            console.error('Error:', error);
            resultArea.textContent = 'Error: ' + error.message;  // Display the error message
        });
}
document.addEventListener('DOMContentLoaded', function() {
    var historyButton = document.getElementById('historyButton');  // Get the history button
    var historyModal = document.getElementById('historyModal');  // Get the modal
    var closeModal = document.querySelector('.close');  // Get the close button

    // Open the modal when the history button is clicked
    historyButton.addEventListener('click', function() {
        historyModal.style.display = 'block';  // Display the modal
        showRecentResults();  // Fetch and display the last 10 results
    });

    // Close the modal when the close button is clicked
    closeModal.addEventListener('click', function() {
        historyModal.style.display = 'none';  // Hide the modal
    });

    // Close the modal when clicking outside of it
    window.addEventListener('click', function(event) {
        if (event.target === historyModal) {
            historyModal.style.display = 'none';  // Hide the modal
        }
    });
});

// Function to show the last 10 results
function showRecentResults() {
    fetch('http://localhost:8080/calculation/getRecent', {  // Correct endpoint
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');  // Handle HTTP errors
            }
            return response.json();  // Parse the JSON response
        })
        .then(data => {
            var recentResultsList = document.getElementById('recentResultsList');  // Ensure the correct element
            if (!recentResultsList) {
                console.error('Element not found: recentResultsList');  // Check for undefined reference
                return;
            }

            recentResultsList.innerHTML = '';  // Clear existing content

            data.forEach(result => {
                var listItem = document.createElement('li');  // Create list items for results
                listItem.textContent = `${result.timestamp}: ${result.input} = ${result.output}`;  // Display format
                recentResultsList.appendChild(listItem);  // Add to the list
            });
        })
        .catch(error => {
            console.error('Error:', error);  // Handle errors
            if (recentResultsList) {
                recentResultsList.textContent = 'Error: ' + error.message;  // Display error message
            }
        });
}


