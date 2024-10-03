import { Component, OnInit } from '@angular/core';

@Component({
  selector: 'app-test-page',
  templateUrl: './test-page.component.html',
  styleUrl: './test-page.component.css'
})
export class TestPageComponent implements OnInit{
  ngOnInit(): void {
    this.loadUnityGame();
  }

  loadUnityGame(): void {
    const buildUrl = 'assets/SkyShooter';
    const loaderUrl = `${buildUrl}/joculescu.loader.js`;

    const config = {
      dataUrl: `${buildUrl}/joculescu.data.br`,
      frameworkUrl: `${buildUrl}/joculescu.framework.js.br`,
      codeUrl: `${buildUrl}/joculescu.wasm.br`,
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
