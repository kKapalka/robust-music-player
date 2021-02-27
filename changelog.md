# Robust Music Player

Planned features:
- no delay between played songs
- custom 10-bar equalizer
- repeat/shuffle
- access to files via folders, not by author/album/playlist (yet)

Intended at this stage to be minimalistic.

Changelog:

### 27.02.2021
- Prepared a RecyclerView containing all folders with music files, and hooked it up to main view
- RecyclerView ViewHolders react to user touch with ripple effect
- Created two different methods retrieving folder data - one for Android Q and newer, and older android versions (There are different media data columns used)
- now it also logs out all music files from selected folder on registered click

### 26.02.2021
- Worked a bit on Inital Activity layout
- Prepared toolbar
- Displaying all folders containing audio files, as an ugly string
- added a temporary element to bottom of the view, to simulate currently selected audio file (test for visibility manipulation)

### 25.02.2021
- Initialized the project
- Set overall color palette
- Planned list of features to implement before launching
- Planned overall navigation and screen transitions
- Prepared splash screen (icon, font, colors)
- Requesting permission for READ_EXTERNAL_STORAGE
- Stores information if user runs this app for the first time
- Handles differently cases, where user used this application before, and where user has permitted (or not) the app to read files