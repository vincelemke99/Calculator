// Global variable to track if the current display value is a result.
let isResultState = false;

// Set up the calculator when the window is fully loaded.
window.onload = function() {
    var resultArea = document.getElementById('resultArea');  // Access the display area for calculations.
    var buttons = Array.from(document.getElementById('buttonContainer').getElementsByTagName('button'));  // Collect all calculator buttons.

    // Attach event listeners to each button.
    buttons.forEach(function(button) {
        button.addEventListener('click', function() {
            var content = this.textContent;  // Get the content of the button clicked.

            switch(content) {
                case 'AC':  // Clear all content in display.
                    resultArea.textContent = '0';
                    break;
                case 'DEL':  // Delete last character if it is not a result.
                    if (isResultState) {
                        resultArea.textContent = resultArea.textContent.slice(0, -1) || '0';
                    }
                    break;
                case '=':  // Calculate and display the result.
                    sendCalculation();
                    break;
                case '+/-':  // Toggle the sign of the current number.
                    let currentValue = parseFloat(resultArea.textContent);
                    resultArea.textContent = (currentValue * -1).toString();
                    break;
                case 'x^y':  // Input exponentiation symbol.
                    resultArea.textContent += '^';
                    break;
                case '÷':  // Input division symbol correctly.
                    resultArea.textContent += '/';
                    break;
                case 'x':  // Input multiplication symbol correctly.
                    resultArea.textContent += '*';
                    break;
                default:  // Handle number and operator inputs.
                    if (resultArea.textContent === '0') {
                        resultArea.textContent = content;
                    } else {
                        resultArea.textContent += content;
                    }
            }
        });
    });
};

// Function to send the current calculation expression to the backend for evaluation.
function sendCalculation() {
    var resultArea = document.getElementById('resultArea');
    var input = resultArea.textContent;  // Get the expression from the display.

    // Make a POST request to the backend with the expression.
    fetch('http://localhost:8080/calculation/add', {
        method: 'POST',
        headers: {
            'Content-Type': 'application/json',
        },
        body: JSON.stringify({input: input})
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var output = parseFloat(data.output);
            resultArea.textContent = Number.isInteger(output) ? output.toString() : data.output;
            isResultState = true;  // Update result state to true.
        })
        .catch(error => {
            console.error('Error:', error);
            resultArea.textContent = 'Error: ' + error.message;
        });
}

// Additional setup for handling modal interactions for showing calculation history.
document.addEventListener('DOMContentLoaded', function() {
    var historyButton = document.getElementById('historyButton');
    var historyModal = document.getElementById('historyModal');
    var closeModal = document.querySelector('.close');

    // Open the history modal when the history button is clicked.
    historyButton.addEventListener('click', function() {
        historyModal.style.display = 'block';
        showRecentResults();
    });

    // Close the history modal when the close button is clicked or when clicking outside the modal.
    closeModal.addEventListener('click', function() {
        historyModal.style.display = 'none';
    });
    window.addEventListener('click', function(event) {
        if (event.target === historyModal) {
            historyModal.style.display = 'none';
        }
    });
});

// Function to fetch and display the last 10 calculation results from the backend.
function showRecentResults() {
    fetch('http://localhost:8080/calculation/getRecent', {
        method: 'GET',
        headers: {
            'Content-Type': 'application/json',
        },
    })
        .then(response => {
            if (!response.ok) {
                throw new Error('Network response was not ok');
            }
            return response.json();
        })
        .then(data => {
            var recentResultsList = document.getElementById('recentResultsList');
            recentResultsList.innerHTML = '';  // Clear any existing content.

            // Populate the list with recent results.
            data.forEach(result => {
                var listItem = document.createElement('li');
                listItem.textContent = `${result.timestamp}: ${result.input} = ${result.output}`;
                recentResultsList.appendChild(listItem);
            });
        })
        .catch(error => {
            console.error('Error:', error);
            recentResultsList.textContent = 'Error: ' + error.message;
        });
}
