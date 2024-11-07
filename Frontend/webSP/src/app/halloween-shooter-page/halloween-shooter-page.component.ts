import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-halloween-shooter-page',
  templateUrl: './halloween-shooter-page.component.html',
  styleUrl: './halloween-shooter-page.component.css'
})
export class HalloweenShooterPageComponent implements OnInit{
  ngOnInit(): void {
    this.loadUnityGame();
  }

  loadUnityGame(): void {
    const buildUrl = 'assets/HalloweenShooter';
    const loaderUrl = `${buildUrl}/waveSh build.loader.js`;

    const config = {
      dataUrl: `${buildUrl}/waveSh build.data.br`,
      frameworkUrl: `${buildUrl}/waveSh build.framework.js.br`,
      codeUrl: `${buildUrl}/waveSh build.wasm.br`,
      streamingAssetsUrl: 'StreamingAssets',
      companyName: 'DefaultCompany',
      productName: 'UnityGame',
      productVersion: '1.0',
      showBanner: (msg: any, type: any) => { console.log(msg, type); },
    };

    const script = document.createElement('script');
    script.src = loaderUrl;
    script.onload = () => {
      (window as any).createUnityInstance(
        document.querySelector('#unity-canvas'),
        config,
        (progress: number) => {
          const progressBarFull = document.querySelector('#unity-progress-bar-full') as HTMLElement;
          if (progressBarFull) progressBarFull.style.width = `${100 * progress}%`;
        }
      ).then((unityInstance: { SetFullscreen: (arg0: number) => void; }) => {
        const fullscreenButton = document.querySelector('#unity-fullscreen-button');
        if (fullscreenButton) {
          fullscreenButton.addEventListener('click', () => {
            unityInstance.SetFullscreen(1);
          });
        }
      }).catch((message: any) => {
        alert(message);
      });
    };
    document.body.appendChild(script);
  }
}
