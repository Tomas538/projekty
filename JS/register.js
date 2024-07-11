function showOptions() {
    document.querySelector('.options-container').style.display = 'block';
}

function hideOptions() {
    document.querySelector('.options-container').style.display = 'none';
}

function showPopup(option) {
    const popupContent = document.getElementById('popup-content');
    
    if (option === 'Register') {
        popupContent.innerHTML = `
            <h2>Registrácia</h2>
            <form>
                <p style="padding-top:20px"><input type="text" placeholder="Meno a Priezvisko" required></p>
                <p><input type="text" placeholder="E-mail" required></p>
                <p><input type="password" placeholder="Heslo" required></p>
                <p><button type="submit" style="width: 180px;">Registrovať</button></p>
                <p style="text-align: left; padding-top: 15px">*Všetky polia sú povinné</p>
            </form>
        `;
    } else if (option === 'Login') {
        popupContent.innerHTML = `
            <h2>Prihlásenie</h2>
            <form>
                <p style="padding-top:20px"><input type="text" placeholder="E-mail" required></p>
                <p><input type="password" placeholder="Heslo" required></p>
                <p><button type="submit" style="width: 180px;">Prihlásiť</button></p>
                <p style="text-align: left; padding-top: 15px">*Všetky polia sú povinné</p>
            </form>
        `;
    }

    document.querySelector('.overlay').style.display = 'flex';
    document.body.classList.add('popup-open');
}

function hidePopup() {
    document.querySelector('.overlay').style.display = 'none';
    document.body.classList.remove('popup-open');
}